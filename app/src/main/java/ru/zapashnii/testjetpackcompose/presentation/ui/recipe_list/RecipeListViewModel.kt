package ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.zapashnii.testjetpackcompose.domain.interactors.search_recipes.ISearchRecipesUseCase
import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.domain.model.params.SearchRecipesParams
import javax.inject.Inject

class RecipeListViewModel(
    private val searchRecipesUseCase: ISearchRecipesUseCase,
) : ViewModel() {

    /** Список рецептов */
    private var _recipes = MutableLiveData<List<Recipe?>?>().apply { value = mutableListOf() }
    val recipes: LiveData<List<Recipe?>?> = _recipes

    /** Выбранная категория */
    private var _selectedCategory = MutableLiveData<FoodCategory>().apply { value = null }
    val selectedCategory: LiveData<FoodCategory> = _selectedCategory

    /** Параметры запроса */
    private var _query = MutableLiveData<String>().apply { value = "" }
    val query: LiveData<String> = _query

    /** Идет загрузка */
    val isLoading = mutableStateOf(false)

    /** Загрузить данные формы */
    fun loadData() {
        newSearch()
    }

    /** Выполнить поиск */
    fun newSearch() {
        viewModelScope.launch {
            isLoading.value = true
            resetSearchState()

            val result = searchRecipesUseCase.getRecipe(SearchRecipesParams(
                page = 1,
                query = _query.value ?: ""
            ))
            _recipes.value = result.results

            isLoading.value = false
        }
    }

    /** Сбросить состояние поиска */
    private fun resetSearchState() {
        _recipes.value = listOf()
    }

    /**
     * Изменить запрос
     *
     * @param query       новый запрос
     */
    fun onQueryChanged(query: String) {
        if(query == FoodCategory.SEARCH.value)
            this._query.value = ""
        else
            this._query.value = query
    }

    /**
     * нажатие на категорию
     *
     * @param category      выбранная категория
     */
    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        _selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    /**
     * Получить индекс выбранной категории
     *
     * @return      индекс категории
     */
    fun getSelectedTabIndex(): Int {
        return getIdFoodCategory(_query.value ?: "")
    }

    /** Фабрика [RecipeListViewModel] */
    class Factory @Inject constructor(
        private val searchRecipesUseCase: ISearchRecipesUseCase,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RecipeListViewModel(
                searchRecipesUseCase = searchRecipesUseCase,
            ) as T
        }
    }
}