package ru.zapashnii.testjetpackcompose.ui.gallery

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.zapashnii.testjetpackcompose.ui.fields.AppButton
import ru.zapashnii.testjetpackcompose.ui.fields.BottomBar
import ru.zapashnii.testjetpackcompose.ui.fields.Toolbar
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
        BottomBar()
    }
}

@Preview
@Composable
fun AppButtonPreview() {
    TestJetpackComposeTheme {
        AppButton()
    }
}