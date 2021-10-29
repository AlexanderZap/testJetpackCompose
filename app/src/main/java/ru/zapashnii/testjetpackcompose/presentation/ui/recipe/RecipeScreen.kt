package ru.zapashnii.testjetpackcompose.presentation.ui.recipe

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * Экран детализации рецепта
 *
 * @param viewModel             viewModel [RecipeViewModel] для этого экрана
 * @param navController         для навигации
 * @param idRecipe              id рецепта
 */
@Composable
fun RecipeScreen(viewModel: RecipeViewModel, navController: NavController, idRecipe: Int?) {
    Text(text = "RecipeScreen idRecipe: $idRecipe")
}