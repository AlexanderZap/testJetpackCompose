package ru.zapashnii.testjetpackcompose.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Класс ответа с сервера
 *
 * @property count          количество рецептов
 * @property next           следующая страница с данными
 * @property previous       предыдущая страница с данными
 * @property results        список рецептов
 */
data class RecipeResponse(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val results: List<Recipe?>? = listOf(),
)
