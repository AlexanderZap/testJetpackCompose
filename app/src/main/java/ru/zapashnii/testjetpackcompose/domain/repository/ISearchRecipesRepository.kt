package ru.zapashnii.testjetpackcompose.domain.repository

import ru.zapashnii.testjetpackcompose.domain.model.RecipeResponse
import ru.zapashnii.testjetpackcompose.domain.model.params.SearchRecipesParams
import ru.zapashnii.testjetpackcompose.domain.repository.base.ICleanableRepository

/** Репозиторий получения данных о рецептах */
interface ISearchRecipesRepository : ICleanableRepository {

    /**
     * Получить рецепты
     *
     * @param searchRecipesParams   Набор параметров для получения рецептов
     * @return                      Информация о рецептах
     */
    suspend fun getRecipe(searchRecipesParams: SearchRecipesParams): RecipeResponse
}