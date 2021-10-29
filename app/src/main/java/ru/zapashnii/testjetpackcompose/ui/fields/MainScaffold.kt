package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.zapashnii.testjetpackcompose.R
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe.RecipeViewModel
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.RecipeListViewModel
import ru.zapashnii.testjetpackcompose.ui.navigation.*
import ru.zapashnii.testjetpackcompose.utils.SnackbarController

/**
 * Главный Scaffold для [MainActivity]
 *
 * @param snackbarController        для snackbar
 * @param viewModelRecipeList       viewModel для экрана [RECIPE_LIST_SCREEN]
 * @param viewModelRecipe           viewModel для экрана [RECIPE_SCREEN]
 * @param onNavigationEvent         событие в навигации
 * @param currentScreen             текущий экран экран
 */
@Composable
fun MainScaffold(
    snackbarController: SnackbarController,
    viewModelRecipeList: RecipeListViewModel,
    viewModelRecipe: RecipeViewModel,
    onNavigationEvent: (String) -> Unit,
    currentScreen: String,
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    //для навигации
    val navController = rememberNavController()

    //настраиваем Toolbar
    var topBar: @Composable () -> Unit = {
        Toolbar(
            textTitleRes = when (currentScreen) {
                RECIPE_LIST_SCREEN -> R.string.search
                HOME_SCREEN -> R.string.home
                SETTING_SCREEN -> R.string.settings
                EMAIL_SCREEN -> R.string.email
                else -> R.string.app_name
            },
            iconImageVector = Icons.Filled.Menu,
            onBackClick = { scope.launch { scaffoldState.drawerState.open() } }
        )
    }
    if (currentScreen == RECIPE_SCREEN) {
        topBar = {
            Toolbar(
                textTitleRes = R.string.app_name,
                onBackClick = { navController.popBackStack() }
            )
        }
    }

    //настраиваем BottomBar
    val bottomBar: @Composable () -> Unit = {
        if (currentScreen != RECIPE_SCREEN) {
            BottomBar(
                onTabBarClick = {
                    snackbarController.getScope().launch {
                        snackbarController.showSnackbar(
                            scaffoldState = scaffoldState,
                            message = "Click $it",
                            actionLabel = "Hide"
                        )
                    }
                },
                countNotification = 100,
                navController = navController
            )
        }
    }

    //настраиваем DrawerContent
    val drawerContent: @Composable ColumnScope.() -> Unit = {
        if (currentScreen != RECIPE_SCREEN) {
            Text("Пункт меню 1", fontSize = 28.sp)
        }
    }

    //собираем Scaffold
    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState },
        topBar = topBar,
        bottomBar = bottomBar,
        drawerContent = drawerContent,
    ) {
        //TODO статичное указание нижнего отступа
        //Box(modifier = Modifier.padding(bottom = 64.dp)) {
        NavigationHost(
            navController = navController,
            viewModelRecipeList = viewModelRecipeList,
            viewModelRecipe = viewModelRecipe,
            onNavigationEvent = { onNavigationEvent(it) }
        )
        //показать snackbar
        /* DefaultSnackbar(
             snackbarHostState = scaffoldState.snackbarHostState,
             onDismiss = {
                 scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
             },
             modifier = Modifier.align(Alignment.BottomEnd)
         )
     }*/
    }
}