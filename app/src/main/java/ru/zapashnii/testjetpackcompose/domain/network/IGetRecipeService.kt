package ru.zapashnii.testjetpackcompose.domain.network

import ru.zapashnii.testjetpackcompose.domain.model.Recipe

/** Сервис получения данных о рецепте по его id */
interface IGetRecipeService {

    /** Получить рецепт по id
     *
     * @param idRecipe    id рецепта
     * @return            рецепт
     */
    suspend fun getRecipeById(idRecipe: Int): Recipe
}