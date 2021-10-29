package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.zapashnii.testjetpackcompose.R
import ru.zapashnii.testjetpackcompose.data.const.EMAIL
import ru.zapashnii.testjetpackcompose.data.const.HOME
import ru.zapashnii.testjetpackcompose.data.const.SEARCH
import ru.zapashnii.testjetpackcompose.data.const.SETTINGS
import ru.zapashnii.testjetpackcompose.ui.navigation.*
import ru.zapashnii.testjetpackcompose.ui.theme.Black2
import ru.zapashnii.testjetpackcompose.ui.theme.Grey1

/**
 * Нижня панель навигации
 *
 * @param layoutId                  префикс идентификатора элемента в его родительском элементе
 * @param onTabBarClick             нажатие на карточку
 * @param defaultColor              цвет иконки без фокуса
 * @param focusColor                цвет иконки на которой фокус
 * @param countNotification         колличесво оповещений(новых сообщений)
 * @param navController             для навигации
 */
@Composable
fun BottomBar(
    layoutId: String = "BottomBar",
    onTabBarClick: (nameTitle: String) -> Unit = {},
    defaultColor: Color = Black2,
    focusColor: Color = Grey1,
    countNotification: Int = 0,
    navController: NavHostController,
) {
    BottomNavigation(
        elevation = 12.dp,
        modifier = Modifier.layoutId("${layoutId}Parent")
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        BottomNavigationItem(
            modifier = Modifier.layoutId("${layoutId}Home"),
            icon = { Icon(Icons.Default.Home, HOME) },
            selected = currentDestination?.hierarchy?.any { it.route == HOME_SCREEN } == true,
            onClick = {
                navController.navigate(HOME_SCREEN) {
                    /* Возвращае к начальному месту назначения графика, чтобы
                     избегать накопления большого количества пунктов назначения
                     на заднем стеке, когда пользователи выбирают элементы */
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Избегает создания нескольких копий одного и того же места назначения, когда повторно выбрае тот же элемент
                    launchSingleTop = true
                    // Восстановить состояние при повторном выборе ранее выбранного элемента
                    restoreState = true
                }
                onTabBarClick(HOME)
            },
            label = {
                Text(
                    modifier = Modifier.layoutId("${layoutId}Home"),
                    text = stringResource(id = R.string.home)
                )
            },
            alwaysShowLabel = false,
            selectedContentColor = focusColor,
            unselectedContentColor = defaultColor,
        )

        BottomNavigationItem(
            modifier = Modifier.layoutId("${layoutId}Search"),
            icon = { Icon(Icons.Default.Search, SEARCH) },
            selected = currentDestination?.hierarchy?.any { it.route == RECIPE_LIST_SCREEN } == true,
            onClick = {
                navController.navigate(RECIPE_LIST_SCREEN) {
                    /* Возвращае к начальному месту назначения графика, чтобы
                     избегать накопления большого количества пунктов назначения
                     на заднем стеке, когда пользователи выбирают элементы */
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Избегает создания нескольких копий одного и того же места назначения, когда повторно выбрае тот же элемент
                    launchSingleTop = true
                    // Восстановить состояние при повторном выборе ранее выбранного элемента
                    restoreState = true
                }
                onTabBarClick(SEARCH)
            },
            alwaysShowLabel = false,
            label = {
                Text(
                    modifier = Modifier.layoutId("${layoutId}Search"),
                    text = stringResource(id = R.string.search)
                )
            },
            selectedContentColor = focusColor,
            unselectedContentColor = defaultColor,
        )

        BottomNavigationItem(
            modifier = Modifier.layoutId("${layoutId}Email"),
            icon = {
                Box {
                    Icon(Icons.Default.Email, EMAIL)
                }
                if (countNotification != 0)
                    Row(
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box(Modifier
                            .clip(MaterialTheme.shapes.large.copy(CornerSize(4.dp)))
                            .wrapContentSize()
                            .background(Color.Red)
                            .align(Alignment.Top),
                            contentAlignment = Alignment.Center) {
                            Text(
                                modifier = Modifier.padding(bottom = 2.dp, start = 2.dp),
                                color = focusColor,
                                text = if (countNotification > 99) "99+" else countNotification.toString(),
                                fontSize = 8.sp,

                                )
                        }
                    }
            },
            selected = currentDestination?.hierarchy?.any { it.route == EMAIL_SCREEN } == true,
            onClick = {
                navController.navigate(EMAIL_SCREEN) {
                    /* Возвращае к начальному месту назначения графика, чтобы
                     избегать накопления большого количества пунктов назначения
                     на заднем стеке, когда пользователи выбирают элементы */
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Избегает создания нескольких копий одного и того же места назначения, когда повторно выбрае тот же элемент
                    launchSingleTop = true
                    // Восстановить состояние при повторном выборе ранее выбранного элемента
                    restoreState = true
                }
                onTabBarClick(EMAIL)
            },
            alwaysShowLabel = false,
            label = {
                Text(
                    modifier = Modifier.layoutId("${layoutId}Email"),
                    text = stringResource(id = R.string.email)
                )
            },
            selectedContentColor = focusColor,
            unselectedContentColor = defaultColor,
        )

        BottomNavigationItem(
            modifier = Modifier.layoutId("${layoutId}Settings"),
            icon = { Icon(Icons.Default.Settings, SETTINGS) },
            selected = currentDestination?.hierarchy?.any { it.route == SETTING_SCREEN } == true,
            onClick = {
                navController.navigate(SETTING_SCREEN) {
                    /* Возвращае к начальному месту назначения графика, чтобы
                     избегать накопления большого количества пунктов назначения
                     на заднем стеке, когда пользователи выбирают элементы */
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Избегает создания нескольких копий одного и того же места назначения, когда повторно выбрае тот же элемент
                    launchSingleTop = true
                    // Восстановить состояние при повторном выборе ранее выбранного элемента
                    restoreState = true
                }
                onTabBarClick(SETTINGS)
            },
            alwaysShowLabel = false,
            label = {
                Text(
                    modifier = Modifier.layoutId("${layoutId}Settings"),
                    text = stringResource(id = R.string.settings)
                )
            },
            selectedContentColor = focusColor,
            unselectedContentColor = defaultColor,
        )
    }
}