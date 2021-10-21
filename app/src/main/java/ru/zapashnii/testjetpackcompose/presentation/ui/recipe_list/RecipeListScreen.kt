package ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.ui.common.VisibilityAnimationComponent
import ru.zapashnii.testjetpackcompose.ui.fields.CircularIndeterminateProgressBar
import ru.zapashnii.testjetpackcompose.ui.fields.LoadingRecipeListShimmer
import ru.zapashnii.testjetpackcompose.ui.fields.RecipeCard
import ru.zapashnii.testjetpackcompose.ui.fields.SearchAppBar

/**
 * Экран список рецептов со строкой поиска сверху
 *
 * @param viewModel         viewModel [RecipeListViewModel] для   этого экрана
 */
@Composable
fun RecipeListScreen(viewModel: RecipeListViewModel) {

    viewModel.loadData()

    //список рецептов
    val recipes by viewModel.recipes.observeAsState(emptyList())
    //параметры запроса
    val query by viewModel.query.observeAsState("")
    //выбранная категория
    val selectedCategory by viewModel.selectedCategory.observeAsState()
    //идет загрузка
    val isLoading = viewModel.isLoading.value

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

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                itemsIndexed(
                    items = recipes ?: mutableListOf()
                ) { index, recipe ->
                    RecipeCard(recipe = recipe ?: Recipe(), onCardClick = {})
                }
            }

            CircularIndeterminateProgressBar(isDisplayed = isLoading)

            /*LoadingRecipeListShimmer(
                colors = listOf(
                    Color.LightGray.copy(alpha = .9f),
                    Color.LightGray.copy(alpha = .3f),
                    Color.LightGray.copy(alpha = .9f),
                ),
                gradientWidth = 200f,
                padding = 8.dp,
                imageHeight = 200.dp,
            )*/
        }
    }
}