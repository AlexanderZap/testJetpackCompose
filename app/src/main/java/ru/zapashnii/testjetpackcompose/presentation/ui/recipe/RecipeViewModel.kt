package ru.zapashnii.testjetpackcompose.presentation.ui.recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.zapashnii.testjetpackcompose.domain.interactors.get_recipe.IGetRecipeUseCase
import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import javax.inject.Inject

/**
 * ViewModel для экрана детализация рецепта [RECIPE_SCREEN]
 *
 * @property getRecipeUseCase       UseCase получения рецепта по его id
 */
class RecipeViewModel(
    private val getRecipeUseCase: IGetRecipeUseCase,
) : ViewModel() {

    /** id рецепта */
    private val idRecipe: MutableState<Int?> = mutableStateOf(null)

    /** Рецепт */
    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    /** Идет загрузка */
    val isLoading = mutableStateOf(false)

    /**
     * Получить id рецепта
     *
     * @param idRecipe      id рецепта
     */
    fun getIdRecipe(idRecipe: Int?) {
        this.idRecipe.value = idRecipe
        searchRecipe()
    }

    /** Выполнить поиск */
    fun searchRecipe() {
        viewModelScope.launch {
            isLoading.value = true

            //имитировать задержку
            delay(1000)

            if (idRecipe.value != null) {
                val result = getRecipeUseCase.getRecipeById(idRecipe = idRecipe.value!!)

                recipe.value = result
            }

            isLoading.value = false
        }
    }

    /** Фабрика [RecipeViewModel] */
    class Factory @Inject constructor(
        private val getRecipeUseCase: IGetRecipeUseCase,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RecipeViewModel(
                getRecipeUseCase = getRecipeUseCase
            ) as T
        }
    }
}