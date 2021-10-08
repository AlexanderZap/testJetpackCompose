package ru.zapashnii.testjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ru.zapashnii.testjetpackcompose.ui.fields.BottomBar
import ru.zapashnii.testjetpackcompose.ui.theme.TestJetpackComposeTheme

class MainActivity : ComponentActivity() {

    private var nameTitle: String = "Home"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val state = remember { mutableStateOf(nameTitle) }
                    BottomBar(
                        onTabBarClick = { state.value = it },
                        nameTitle = state.value
                    )
                }
            }
        }
    }
}