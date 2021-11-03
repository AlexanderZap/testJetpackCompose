package ru.zapashnii.testjetpackcompose.presentation.ui.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.zapashnii.testjetpackcompose.ui.fields.CircularIndeterminateProgressBar
import ru.zapashnii.testjetpackcompose.ui.fields.RecipeView

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

    //рецепт
    val recipe = viewModel.recipe.observeAsState().value
    //идет загрузка
    val isLoading = viewModel.isLoading.value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading && recipe == null) Text(text = "LOADING...")
        else recipe?.let {
            RecipeView(
                recipe = recipe,
            )
        }
        CircularIndeterminateProgressBar(isDisplayed = isLoading)
    }
}