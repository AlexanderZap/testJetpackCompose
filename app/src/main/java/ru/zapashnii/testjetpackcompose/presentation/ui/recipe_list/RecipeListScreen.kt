package ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.ui.fields.RecipeCard
import ru.zapashnii.testjetpackcompose.ui.fields.SearchAppBar

@Composable
fun RecipeListScreen(viewModel: RecipeListViewModel) {

    viewModel.loadData()

    //список рецептов
    val recipes by viewModel.recipes.observeAsState(emptyList())
    //параметры запроса
    val query by viewModel.query.observeAsState("")
    //выбранная категория
    val selectedCategory by viewModel.selectedCategory.observeAsState()

    Column {
        SearchAppBar(
            query = query,
            onQueryChanged = viewModel::onQueryChanged,
            onExecuteSearch = viewModel::newSearch,
            categories = getAllFoodCategories(),
            getSelectedTabIndex = viewModel.getSelectedTabIndex(),
            selectedCategory = selectedCategory,
            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged
        )

        LazyColumn {
            itemsIndexed(
                items = recipes ?: mutableListOf()
            ) { index, recipe ->
                RecipeCard(recipe = recipe ?: Recipe(), onCardClick = {})
            }
        }
    }
}