package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import ru.zapashnii.testjetpackcompose.R

/**
 * Большая кнопка внутри контейнера с elevation
 *
 * @param modifier      модификатор для изменения контейнера кнопки
 * @param layoutId      префикс идентификатора элемента в его родительском элементе
 * @param textResId     ресурс на текст кнопики
 * @param enabled       кнопка активна
 * @param onClick       нажатие на кнопку
 */
@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    layoutId: String = "appButton",
    textResId: Int = R.string.home,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Surface(Modifier.layoutId("${layoutId}Root"), elevation = 4.dp) {
        Box(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                .height(40.dp),
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .layoutId("${layoutId}Clickable"),
                enabled = enabled,
                colors = ButtonDefaults.buttonColors(disabledContentColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                onClick = onClick
            ) {
                Text(text = stringResource(id = textResId).toUpperCase(Locale.current))
            }
        }
    }
}