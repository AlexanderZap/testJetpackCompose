package ru.zapashnii.testjetpackcompose.domain.interactors.search_recipes

import ru.zapashnii.testjetpackcompose.domain.interactors.base.UseCase
import ru.zapashnii.testjetpackcompose.domain.model.RecipeResponse
import ru.zapashnii.testjetpackcompose.domain.model.params.SearchRecipesParams
import ru.zapashnii.testjetpackcompose.domain.repository.ISearchRecipesRepository
import javax.inject.Inject

/**
 * UseCase для получения рецептов
 *
 * @property repository          репозиторий с данными о рцептах
 */
class SearchRecipesUseCase @Inject constructor(
    private val repository: ISearchRecipesRepository,
) : UseCase<RecipeResponse, SearchRecipesParams>(), ISearchRecipesUseCase {

    override suspend fun run(params: SearchRecipesParams): RecipeResponse {
        return repository.getRecipe(params)
    }

    override suspend fun getRecipe(searchRecipesParams: SearchRecipesParams): RecipeResponse {
        return run(searchRecipesParams)
    }

    override fun dispatch() {
        repository.clear()
    }
}