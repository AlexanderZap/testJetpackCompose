package ru.zapashnii.testjetpackcompose.domain.repository

import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.domain.repository.base.ICleanableRepository

/** Репозиторий получения данных о рецепте по его id */
interface IGetRecipeRepository : ICleanableRepository {

    /** Получить рецепт по id
     *
     * @param idRecipe    id рецепта
     * @return            рецепт
     */
    suspend fun getRecipeById(idRecipe: Int): Recipe
}