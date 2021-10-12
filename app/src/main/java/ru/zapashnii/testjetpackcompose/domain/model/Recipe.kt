package ru.zapashnii.testjetpackcompose.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Класс рецепта
 *
 * @property pk                     первичный ключ
 * @property title                  название
 * @property publisher              издатель
 * @property featuredImage          популярные изображения
 * @property rating                 рейтинг
 * @property sourceUrl              исходный URL
 * @property description            описание
 * @property cookingInstructions    инструкция по приготовлению
 * @property ingredients            список ингредиентов
 * @property dateAdded              дата добавления
 * @property dateUpdated            дата обновления
 * @property longDateAdded          длинная дата добавления
 * @property longDateUpdated        длинная дата обновления
 */
data class Recipe(
    @SerializedName("pk")
    val pk: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("publisher")
    val publisher: String? = null,
    @SerializedName("featured_image")
    val featuredImage: String? = null,
    @SerializedName("rating")
    val rating: Int? = 0,
    @SerializedName("source_url")
    val sourceUrl: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("cooking_instructions")
    val cookingInstructions: String? = null,
    @SerializedName("ingredients")
    val ingredients: List<String?>? = listOf(),
    @SerializedName("date_added")
    val dateAdded: String? = null,
    @SerializedName("date_updated")
    val dateUpdated: String? = null,
    @SerializedName("long_date_added")
    val longDateAdded: Int? = 0,
    @SerializedName("long_date_updated")
    val longDateUpdated: Int? = 0,
)