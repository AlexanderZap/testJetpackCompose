package ru.zapashnii.testjetpackcompose.domain.model.params

import java.io.Serializable

/**
 * Набор параметров для получения рецептов
 *
 * @param page      номер страницы
 * @param query     запрос из ключевых слов для поиска рецепта. Пример: "beef carrot potato onion"
 */
data class SearchRecipesParams(
    val page: Int,
    val query: String,
) : Serializable