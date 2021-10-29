package ru.zapashnii.testjetpackcompose.domain.interactors.get_recipe

import ru.zapashnii.testjetpackcompose.domain.interactors.base.UseCase
import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.domain.repository.IGetRecipeRepository
import javax.inject.Inject

/**
 * UseCase для получения рецепта по id
 *
 * @property repository          репозиторий с данными о рцепте
 */
class GetRecipeUseCase @Inject constructor(
    private val repository: IGetRecipeRepository,
) : UseCase<Recipe, Int>(), IGetRecipeUseCase {

    override suspend fun run(params: Int): Recipe {
        return repository.getRecipeById(params)
    }

    override suspend fun getRecipeById(idRecipe: Int): Recipe {
        return run(idRecipe)
    }

    override fun dispatch() {
        repository.clear()
    }
}