package ru.zapashnii.testjetpackcompose.domain.network

import ru.zapashnii.testjetpackcompose.domain.model.RecipeResponse
import ru.zapashnii.testjetpackcompose.domain.model.params.SearchRecipesParams

/** Сервис получения данных о рецептах */
interface ISearchRecipesService {

    /**
     * Получить рецепты
     *
     * @param searchRecipesParams   Набор параметров для получения рецептов
     * @return                      Информация о рецептах
     */
    suspend fun getRecipe(searchRecipesParams: SearchRecipesParams): RecipeResponse
}