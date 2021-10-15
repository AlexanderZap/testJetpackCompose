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
    var pk: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("publisher")
    var publisher: String? = null,
    @SerializedName("featured_image")
    var featuredImage: String? = null,
    @SerializedName("rating")
    var rating: Int? = 0,
    @SerializedName("source_url")
    var sourceUrl: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("cooking_instructions")
    var cookingInstructions: String? = null,
    @SerializedName("ingredients")
    var ingredients: List<String?>? = listOf(),
    @SerializedName("date_added")
    var dateAdded: String? = null,
    @SerializedName("date_updated")
    var dateUpdated: String? = null,
    @SerializedName("long_date_added")
    var longDateAdded: Int? = 0,
    @SerializedName("long_date_updated")
    var longDateUpdated: Int? = 0,
)