package ru.zapashnii.testjetpackcompose.domain.interactors.search_recipes

import ru.zapashnii.testjetpackcompose.domain.interactors.base.IDispatchUseCase
import ru.zapashnii.testjetpackcompose.domain.model.RecipeResponse
import ru.zapashnii.testjetpackcompose.domain.model.params.SearchRecipesParams

/** UseCase для получения рецептов */
interface ISearchRecipesUseCase : IDispatchUseCase {

    /**
     * Получить рецепты
     *
     * @param searchRecipesParams   Набор параметров для получения рецептов
     * @return                      Информация о рецептах
     */
    suspend fun getRecipe(searchRecipesParams: SearchRecipesParams): RecipeResponse
}