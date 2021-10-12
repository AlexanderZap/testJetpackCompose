package ru.zapashnii.testjetpackcompose.data.network

import ru.zapashnii.testjetpackcompose.domain.model.RecipeResponse
import ru.zapashnii.testjetpackcompose.domain.model.params.SearchRecipesParams
import ru.zapashnii.testjetpackcompose.domain.network.ISearchRecipesService
import ru.zapashnii.testjetpackcompose.network.Api
import javax.inject.Inject

/**
 * Получить данные о рецептах
 *
 * @property api            интерфейс с договорами(контрактами) получения данных с сервера
 */
class SearchRecipesService @Inject constructor(
    private val api: Api,
) : ISearchRecipesService {

    /**
     * Получить рецепты
     *
     * @param searchRecipesParams   Набор параметров для получения рецептов
     * @return                      Информация о рецептах
     */
    override suspend fun getRecipe(searchRecipesParams: SearchRecipesParams): RecipeResponse {
        return api.search(
            page = searchRecipesParams.page,
            query = searchRecipesParams.query,
        )
    }
}