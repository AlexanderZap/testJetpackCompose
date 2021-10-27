package ru.zapashnii.testjetpackcompose.ui.gallery

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.zapashnii.testjetpackcompose.ui.fields.*
import ru.zapashnii.testjetpackcompose.ui.theme.TestJetpackComposeTheme

/* Галерея компонетов приложения */

@Preview
@Composable
fun ToolbarPreview1() {
    TestJetpackComposeTheme {
        Toolbar()
    }
}

@Preview
@Composable
fun ToolbarPreview2() {
    TestJetpackComposeTheme {
        Toolbar(iconImageVector = Icons.Filled.Menu)
    }
}

@Preview
@Composable
fun TabBarCardPreview() {
    TestJetpackComposeTheme {
        BottomBar(countNotification = 100)
    }
}

@Preview
@Composable
fun AppButtonPreview() {
    TestJetpackComposeTheme {
        AppButton()
    }
}

@Preview
@Composable
fun AppFoodCategoryScrollRowPreview() {
    TestJetpackComposeTheme {
        FoodCategoryScrollRow()
    }
}

@Preview
@Composable
fun AppFoodCategoryChipPreview() {
    TestJetpackComposeTheme {
        FoodCategoryChip(category = "Chicken", onExecuteSearch = {}, onSelectedCategoryChanged = {})
    }
}

@Preview
@Composable
fun AnimatedFavoriteButtonPreview() {
    TestJetpackComposeTheme {
        val state = remember { mutableStateOf(false) }
        AnimatedFavoriteButton(isButtonState = state, onClickHeartButton = { state.value = !state.value })
    }
}

@Preview
@Composable
fun ShimmerRecipeCardItemPreview() {
    TestJetpackComposeTheme {
        ShimmerRecipeCardItem(
            colors = listOf(
                Color.LightGray.copy(alpha = .9f),
                Color.LightGray.copy(alpha = .3f),
                Color.LightGray.copy(alpha = .9f),
            ),
            xShimmer = 400F,
            yShimmer = 400F,
            cardHeight = 200.dp,
            gradientWidth = 200f,
            padding = 16.dp
        )
    }
}