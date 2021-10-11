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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.zapashnii.testjetpackcompose.R

/**
 * Нижня панель навигации
 *
 * @param layoutId          префикс идентификатора элемента в его родительском элементе
 * @param onTabBarClick     нажатие на карточку
 * @param nameTitle         название карточки, на которой фокус
 */
@Composable
fun BottomBar(
    layoutId: String = "BottomBar",
    onTabBarClick: (nameTitle: String) -> Unit = {},
    nameTitle: String = "Home",
) {
    BottomNavigation(
        elevation = 12.dp,
        modifier = Modifier.layoutId("${layoutId}Parent")
    ) {
        BottomNavigationItem(
            modifier = Modifier.layoutId("${layoutId}Home"),
            icon = { Icon(Icons.Default.Home, "Home") },
            selected = nameTitle == "Home",
            onClick = { onTabBarClick("Home") },
            label = {
                Text(
                    modifier = Modifier.layoutId("${layoutId}Home"),
                    text = stringResource(id = R.string.home)
                )
            },
            alwaysShowLabel = false,
            selectedContentColor = Color.Red,
            unselectedContentColor = Color.Gray,
        )

        BottomNavigationItem(
            modifier = Modifier.layoutId("${layoutId}Search"),
            icon = { Icon(Icons.Default.Search, "Search") },
            selected = nameTitle == "Search",
            onClick = { onTabBarClick("Search") },
            alwaysShowLabel = false,
            label = {
                Text(
                    modifier = Modifier.layoutId("${layoutId}Search"),
                    text = stringResource(id = R.string.search)
                )
            },
            selectedContentColor = Color.Red,
            unselectedContentColor = Color.Gray,
        )

        BottomNavigationItem(
            modifier = Modifier.layoutId("${layoutId}Email"),
            icon = { Icon(Icons.Default.Email, "Email") },
            selected = nameTitle == "Email",
            onClick = { onTabBarClick("Email") },
            alwaysShowLabel = false,
            label = {
                Text(
                    modifier = Modifier.layoutId("${layoutId}Email"),
                    text = stringResource(id = R.string.email)
                )
            },
            selectedContentColor = Color.Red,
            unselectedContentColor = Color.Gray,
        )

        BottomNavigationItem(
            modifier = Modifier.layoutId("${layoutId}Settings"),
            icon = {
                Box {
                    Icon(Icons.Default.Settings, "Settings")
                }
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
                            modifier = Modifier.padding(end = 2.dp, start = 2.dp),
                            color = Color.White,
                            text = "99",
                            fontSize = 6.sp,

                            )
                    }
                }
            },
            selected = nameTitle == "Settings",
            onClick = { onTabBarClick("Settings") },
            alwaysShowLabel = false,
            label = {
                Text(
                    modifier = Modifier.layoutId("${layoutId}Settings"),
                    text = stringResource(id = R.string.settings)
                )
            },
            selectedContentColor = Color.Red,
            unselectedContentColor = Color.Gray,
        )
    }
}