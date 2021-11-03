package ru.zapashnii.testjetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.lifecycleScope
import ru.zapashnii.testjetpackcompose.di.MainApp
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe.RecipeViewModel
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.RecipeListViewModel
import ru.zapashnii.testjetpackcompose.ui.fields.MainScaffold
import ru.zapashnii.testjetpackcompose.ui.navigation.HOME_SCREEN
import ru.zapashnii.testjetpackcompose.ui.theme.TestJetpackComposeTheme
import ru.zapashnii.testjetpackcompose.utils.SnackbarController
import ru.zapashnii.testjetpackcompose.utils.appComponent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factoryRecipeList: RecipeListViewModel.Factory
    private val viewModelRecipeList: RecipeListViewModel by viewModels { factoryRecipeList }

    @Inject
    lateinit var factoryRecipe: RecipeViewModel.Factory
    private val viewModelRecipe: RecipeViewModel by viewModels { factoryRecipe }

    //для snackbar
    private val snackbarController = SnackbarController(lifecycleScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        val currentScreen = mutableStateOf(HOME_SCREEN)

        setContent {
            TestJetpackComposeTheme(darkTheme = MainApp.instance.isDark.value) {
                Surface(color = MaterialTheme.colors.background) {
                    MainScaffold(
                        snackbarController = snackbarController,
                        viewModelRecipeList = viewModelRecipeList,
                        viewModelRecipe = viewModelRecipe,
                        onNavigationEvent = { currentScreen.value = it },
                        currentScreen = currentScreen.value
                    )
                }
            }
        }
    }
}