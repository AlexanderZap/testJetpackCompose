package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.getAllFoodCategories

/**
 * Прокручиваемая строка
 *
 * @param layoutId              префикс идентификатора элемента в его родительском элементе
 * @param selectedTabIndex      индекс текущей выбранной вкладки
 */
@Composable
fun FoodCategoryScrollRow(
    layoutId: String = "FoodCategoryScrollRow",
    selectedTabIndex: Int = 0,
) {
    ScrollableTabRow(
        modifier = Modifier
            .fillMaxWidth()
            .layoutId("${layoutId}TabRow")
            .padding(top = 8.dp, bottom = 8.dp),
        selectedTabIndex = selectedTabIndex,
        edgePadding = 16.dp,
        backgroundColor = Color.White) {

        for (category in getAllFoodCategories()) {
            FoodCategoryChip(
                category = category.value,
                isSelected = false,
                onExecuteSearch = { },
                onSelectedCategoryChanged = {}
            )
        }
    }
}