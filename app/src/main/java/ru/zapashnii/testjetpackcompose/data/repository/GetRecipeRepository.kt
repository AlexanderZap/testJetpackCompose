package ru.zapashnii.testjetpackcompose.data.repository

import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.domain.network.IGetRecipeService
import ru.zapashnii.testjetpackcompose.domain.repository.IGetRecipeRepository
import javax.inject.Inject

/**
 * Получить и закэшировать данные о рецепте по его id
 *
 * @property service        сервис для получения данных о рецепте
 */
class GetRecipeRepository @Inject constructor(
    private val service: IGetRecipeService,
) : IGetRecipeRepository {

    private var cache: HashMap<Int, Recipe> = hashMapOf()

    override suspend fun getRecipeById(idRecipe: Int): Recipe {
        return if (cache[idRecipe] == null) {
            service.getRecipeById(idRecipe).apply {
                cache[idRecipe] = this
            }
        } else {
            cache[idRecipe] ?: Recipe()
        }
    }

    override fun clear() {
        cache.clear()
    }
}