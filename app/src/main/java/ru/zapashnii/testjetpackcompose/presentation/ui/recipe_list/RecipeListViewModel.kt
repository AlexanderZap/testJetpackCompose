package ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.zapashnii.testjetpackcompose.data.const.PAGE_SIZE
import ru.zapashnii.testjetpackcompose.domain.interactors.search_recipes.ISearchRecipesUseCase
import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.domain.model.params.SearchRecipesParams
import javax.inject.Inject

/**
 * ViewModel для экрана списка рецептов(поиска) [RECIPE_LIST_SCREEN]
 *
 * @property searchRecipesUseCase       UseCase получения списка рецептов
 */
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

    /** Номер страницы с рецептами */
    val page = mutableStateOf(1)

    /** Положение прокрутки списка рецептов */
    private var recipeListScrollPosition = 0

    /** Загрузить данные формы */
    init {
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

    /** Загрузить следующую страницу с рецептами */
    fun nextPage() {
        viewModelScope.launch {
            // предотвратить дублирование событий из-за быстрой перекомпоновки
            if ((recipeListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
                isLoading.value = true
                incrementPage()
                // просто чтобы показать разбиение на страницы, api работает быстро
                delay(1000)

                if (page.value > 1) {
                    val result = searchRecipesUseCase.getRecipe(SearchRecipesParams(
                        page = page.value,
                        query = _query.value ?: ""
                    )).results
                    appendRecipes(result)
                }
                isLoading.value = false
            }
        }
    }

    /**
     * Добавить новые рецепты к текущему списку рецептов
     *
     * @param recipes       список рецептов
     */
    private fun appendRecipes(recipes: List<Recipe?>?) {
        val current = ArrayList(this.recipes.value)
        recipes?.let { current.addAll(it) }
        _recipes.value = current
    }

    /** Увеличить номер страницы */
    private fun incrementPage() {
        page.value = page.value + 1
    }

    /**
     * Изменить позицию положения прокрутки списка рецептов
     *
     * @param position      новая позиция
     */
    fun onChangeRecipeScrollPosition(position: Int) {
        recipeListScrollPosition = position
    }

    /** Сбросить состояние поиска */
    private fun resetSearchState() {
        _recipes.value = listOf()
        page.value = 1
        onChangeRecipeScrollPosition(0)
    }

    /**
     * Изменить запрос
     *
     * @param query       новый запрос
     */
    fun onQueryChanged(query: String) {
        if (query == FoodCategory.SEARCH.value)
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