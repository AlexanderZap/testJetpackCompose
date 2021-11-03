package ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.zapashnii.testjetpackcompose.data.const.PAGE_SIZE
import ru.zapashnii.testjetpackcompose.di.MainApp
import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.ui.common.VisibilityAnimationComponent
import ru.zapashnii.testjetpackcompose.ui.fields.*
import ru.zapashnii.testjetpackcompose.ui.navigation.RECIPE_SCREEN

/**
 * Экран список рецептов со строкой поиска сверху
 *
 * @param viewModel             viewModel [RecipeListViewModel] для этого экрана
 * @param navController         для навигации
 */
@Composable
fun RecipeListScreen(viewModel: RecipeListViewModel, navController: NavController) {

    //список рецептов
    val recipes by viewModel.recipes.observeAsState(emptyList())
    //параметры запроса
    val query by viewModel.query.observeAsState("")
    //выбранная категория
    val selectedCategory by viewModel.selectedCategory.observeAsState()
    //идет загрузка
    val isLoading = viewModel.isLoading.value
    //номер страницы с рецептами
    val page = viewModel.page.value

    Column {
        SearchAppBar(
            query = query,
            onQueryChanged = viewModel::onQueryChanged,
            onExecuteSearch = viewModel::newSearch,
            categories = getAllFoodCategories(),
            getSelectedTabIndex = viewModel.getSelectedTabIndex(),
            selectedCategory = selectedCategory,
            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
            onToggleTheme = { MainApp.instance.toggleLightTheme() }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            LazyColumn {
                itemsIndexed(
                    items = recipes ?: mutableListOf()
                ) { index, recipe ->
                    viewModel.onChangeRecipeScrollPosition(index)
                    if ((index + 1) >= (page * PAGE_SIZE) && !isLoading) {
                        viewModel.nextPage()
                    }
                    RecipeCard(
                        recipe = recipe ?: Recipe(),
                        onCardClick = {
                            if (recipe?.pk != null) {
                                //передать id рецепта
                                navController.currentBackStackEntry?.arguments?.putInt("recipeId",
                                    recipe.pk!!)
                                navController.navigate(RECIPE_SCREEN)
                            }
                        }
                    )
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