package ru.zapashnii.testjetpackcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe.RecipeScreen
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe.RecipeViewModel
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.RecipeListScreen
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.RecipeListViewModel

/**
 * Навигация в приложении
 *
 * @param navController             для отслеживания состояния
 * @param modifier                  модификатор для NavHost
 * @param viewModelRecipeList       viewModel для экрана [RECIPE_LIST_SCREEN]
 * @param viewModelRecipe           viewModel для экрана [RECIPE_SCREEN]
 */
@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModelRecipeList: RecipeListViewModel,
    viewModelRecipe: RecipeViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = RECIPE_LIST_SCREEN,
        modifier = modifier,
    ) {
        composable(RECIPE_LIST_SCREEN) {
            RecipeListScreen(
                viewModel = viewModelRecipeList,
                navController = navController
            )
        }
        composable(RECIPE_SCREEN) {
            RecipeScreen(
                viewModel = viewModelRecipe,
                navController = navController
            )
        }
    }
}