package ru.zapashnii.testjetpackcompose.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.domain.model.RecipeResponse

interface Api {

    /**
     * Получить все рецепты по ключевым словам
     *
     * @param token     токен
     * @param page      номер страницы
     * @param query     запрос из ключевых слов для поиска рецепта. Пример: beef carrot potato onion
     * @return
     */
    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String,
    ): RecipeResponse

    /**
     * Получить рецепт по id
     *
     * @param token     токен
     * @param id        id рецепта
     * @return          рецепт
     */
    @GET("get")
    suspend fun get(
        @Header("Authorization") token: String,
        @Query("id") id: Int,
    ): Recipe
}