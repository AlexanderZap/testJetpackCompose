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
data class Response(
    @SerializedName("count")
    val count: Int? = 0, // 118
    @SerializedName("next")
    val next: String? = null, // http://127.0.0.1:8000/api/recipe/search/?page=3&query=beef+carrot+potato+onion
    @SerializedName("previous")
    val previous: String? = null, // https://food2fork.ca/api/recipe/search/?query=beef+carrot+potato+onion
    @SerializedName("results")
    val results: List<Recipe?>? = listOf(),
)
