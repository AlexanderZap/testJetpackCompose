package ru.zapashnii.testjetpackcompose.domain.interactors.get_recipe

import ru.zapashnii.testjetpackcompose.domain.interactors.base.IDispatchUseCase
import ru.zapashnii.testjetpackcompose.domain.model.Recipe

/** UseCase для получения рецепта по его id */
interface IGetRecipeUseCase : IDispatchUseCase {

    /**
     * Получить рецепт по id
     *
     * @param idRecipe    id рецепта
     * @return            рецепт
     */
    suspend fun getRecipeById(idRecipe: Int): Recipe
}