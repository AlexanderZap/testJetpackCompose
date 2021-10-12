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
    val pk: Int? = null, // 583
    @SerializedName("title")
    val title: String? = null, // Pizza Potato Skins
    @SerializedName("publisher")
    val publisher: String? = null, // mitch
    @SerializedName("featured_image")
    val featuredImage: String? = null, // https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/583/featured_image.png
    @SerializedName("rating")
    val rating: Int? = 0, // 16
    @SerializedName("source_url")
    val sourceUrl: String? = null, // http://thepioneerwoman.com/cooking/2013/04/pizza-potato-skins/
    @SerializedName("description")
    val description: String? = null, // N/A
    @SerializedName("cooking_instructions")
    val cookingInstructions: String? = null, // null
    @SerializedName("ingredients")
    val ingredients: List<String?>? = listOf(),
    @SerializedName("date_added")
    val dateAdded: String? = null, // November 11 2020
    @SerializedName("date_updated")
    val dateUpdated: String? = null, // November 11 2020
    @SerializedName("long_date_added")
    val longDateAdded: Int? = 0, // 1606349126
    @SerializedName("long_date_updated")
    val longDateUpdated: Int? = 0, // 1606349126
)