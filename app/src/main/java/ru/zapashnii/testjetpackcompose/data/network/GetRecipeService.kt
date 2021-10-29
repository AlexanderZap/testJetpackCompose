package ru.zapashnii.testjetpackcompose.data.network

import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.domain.network.IGetRecipeService
import ru.zapashnii.testjetpackcompose.network.Api
import javax.inject.Inject

/**
 * Получить данные о рецепте по его id
 *
 * @property api            интерфейс с договорами(контрактами) получения данных с сервера
 */
class GetRecipeService @Inject constructor(
    private val api: Api,
) : IGetRecipeService {

    /** Получить рецепт по id
     *
     * @param idRecipe    id рецепта
     * @return            рецепт
     */
    override suspend fun getRecipeById(idRecipe: Int): Recipe {
        return api.get(id = idRecipe)
    }
}