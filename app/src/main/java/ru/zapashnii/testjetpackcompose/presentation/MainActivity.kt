package ru.zapashnii.testjetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.RecipeListScreen
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.RecipeListViewModel
import ru.zapashnii.testjetpackcompose.ui.theme.TestJetpackComposeTheme
import ru.zapashnii.testjetpackcompose.utils.appComponent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private var nameTitle: String = "Home"

    @Inject
    lateinit var factory: RecipeListViewModel.Factory
    private val viewModel: RecipeListViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        setContent {
            TestJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    RecipeListScreen(viewModel)
                   /* val state = remember { mutableStateOf(nameTitle) }
                    BottomBar(
                        onTabBarClick = { state.value = it },
                        nameTitle = state.value
                    )*/
                }
            }
        }
    }
}