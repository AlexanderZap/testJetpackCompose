package ru.zapashnii.testjetpackcompose.data.repository

import ru.zapashnii.testjetpackcompose.domain.model.RecipeResponse
import ru.zapashnii.testjetpackcompose.domain.model.params.SearchRecipesParams
import ru.zapashnii.testjetpackcompose.domain.network.ISearchRecipesService
import ru.zapashnii.testjetpackcompose.domain.repository.ISearchRecipesRepository
import javax.inject.Inject

/**
 * Получить и закэшировать данные о рецептах
 *
 * @property service        сервис для получения данных о рецептах
 */
class SearchRecipesRepository @Inject constructor(
    private val service: ISearchRecipesService,
) : ISearchRecipesRepository {

    private var cache: HashMap<SearchRecipesParams, RecipeResponse> = hashMapOf()

    override suspend fun getRecipe(searchRecipesParams: SearchRecipesParams): RecipeResponse {
        return if (cache[searchRecipesParams] == null) {
            service.getRecipe(searchRecipesParams).apply {
                cache[searchRecipesParams] = this
            }
        } else {
            cache[searchRecipesParams] ?: RecipeResponse()
        }
    }

    override fun clear() {
        cache.clear()
    }
}