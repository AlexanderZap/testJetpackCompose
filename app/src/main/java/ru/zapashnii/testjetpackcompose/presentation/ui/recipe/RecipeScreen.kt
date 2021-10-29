package ru.zapashnii.testjetpackcompose.presentation.ui.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    viewModel.getIdRecipe(idRecipe)

    //идет загрузка
    val isLoading = viewModel.isLoading.value
    //рецепт
    val recipe = viewModel.recipe.value

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = recipe?.let { recipe -> "Selected recipeId: ${recipe.pk}" } ?: "LOADING...")
    }
}