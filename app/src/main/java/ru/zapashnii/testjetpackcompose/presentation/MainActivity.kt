package ru.zapashnii.testjetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.RecipeListScreen
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.RecipeListViewModel
import ru.zapashnii.testjetpackcompose.ui.fields.BottomBar
import ru.zapashnii.testjetpackcompose.ui.fields.Toolbar
import ru.zapashnii.testjetpackcompose.ui.theme.TestJetpackComposeTheme
import ru.zapashnii.testjetpackcompose.utils.appComponent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private var nameTitle: String = "Home"

    @Inject
    lateinit var factoryRecipeList: RecipeListViewModel.Factory
    private val viewModelRecipeList: RecipeListViewModel by viewModels { factoryRecipeList }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        setContent {
            TestJetpackComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val scaffoldState = rememberScaffoldState()
                    val scope = rememberCoroutineScope()

                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            Toolbar(
                                iconImageVector = Icons.Filled.Menu,
                                onBackClick = { scope.launch { scaffoldState.drawerState.open() } }
                            )
                        },
                        bottomBar = {
                            val state = remember { mutableStateOf(nameTitle) }
                            BottomBar(
                                onTabBarClick = { state.value = it },
                                nameTitle = state.value,
                                countNotification = 100
                            )
                        },
                        drawerContent = { Text("Пункт меню 1", fontSize = 28.sp) },
                    ) {
                        RecipeListScreen(viewModelRecipeList)
                    }
                }
            }
        }
    }
}