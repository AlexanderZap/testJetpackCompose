package ru.zapashnii.testjetpackcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.zapashnii.testjetpackcompose.presentation.ui.email.EmailScreen
import ru.zapashnii.testjetpackcompose.presentation.ui.home.HomeScreen
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe.RecipeScreen
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe.RecipeViewModel
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.RecipeListScreen
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.RecipeListViewModel
import ru.zapashnii.testjetpackcompose.presentation.ui.setting.SettingScreen

/**
 * Навигация в приложении
 *
 * @param navController             для навигации (для отслеживания состояния)
 * @param modifier                  модификатор для NavHost
 * @param viewModelRecipeList       viewModel для экрана [RECIPE_LIST_SCREEN]
 * @param viewModelRecipe           viewModel для экрана [RECIPE_SCREEN]
 */
@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModelRecipeList: RecipeListViewModel,
    viewModelRecipe: RecipeViewModel,
    onNavigationEvent: (String) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = HOME_SCREEN,
        modifier = modifier,
    ) {
        composable(HOME_SCREEN) {
            HomeScreen(
                navController = navController
            )
            onNavigationEvent(HOME_SCREEN)
        }
        composable(RECIPE_LIST_SCREEN) {
            RecipeListScreen(
                viewModel = viewModelRecipeList,
                navController = navController
            )
            onNavigationEvent(RECIPE_LIST_SCREEN)
        }
        composable(RECIPE_SCREEN) {
            RecipeScreen(
                viewModel = viewModelRecipe,
                navController = navController
            )
            onNavigationEvent(RECIPE_SCREEN)
        }
        composable(SETTING_SCREEN) {
            SettingScreen(
                navController = navController
            )
            onNavigationEvent(SETTING_SCREEN)
        }
        composable(EMAIL_SCREEN) {
            EmailScreen(
                navController = navController
            )
            onNavigationEvent(EMAIL_SCREEN)
        }
    }
}