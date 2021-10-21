package ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list

import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.FoodCategory.*

/**
 * Класс перечисления категорий продуктов питания, который используется в строке поиска
 *
 * @property value
 */
enum class FoodCategory(val value: String) {
    SEARCH("All Recipes"),
    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    MILK("Milk"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donut"),
}

/**
 * Получить список всех категорий
 *
 * @return  список всех категорий
 */
fun getAllFoodCategories(): List<FoodCategory> {
    return listOf(SEARCH,CHICKEN, BEEF, SOUP, DESSERT, VEGETARIAN, MILK, VEGAN, PIZZA, DONUT)
}

/**
 * Получить категорию по заданному значению
 *
 * @param value          заданное значение
 * @return
 */
fun getFoodCategory(value: String): FoodCategory? {
    val map = FoodCategory.values().associateBy(FoodCategory::value)
    return map[value]
}

/**
 * Получить Id категории по его названию
 *
 * @param value     название категории
 * @return          Id категории
 */
fun getIdFoodCategory(value: String): Int {
    for (category in getAllFoodCategories()) {
        if (category.value == value) {
            return getAllFoodCategories().indexOf(category)
        }
    }
    return 0
}