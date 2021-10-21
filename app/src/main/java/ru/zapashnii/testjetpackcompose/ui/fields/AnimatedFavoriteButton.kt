package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import ru.zapashnii.testjetpackcompose.R
import ru.zapashnii.testjetpackcompose.utils.loadPicture

/**
 * Кнопка понравиться с анимацией
 *
 * @param isButtonState         кнока уже нажата (лайк уже стоит)
 * @param onClickHeartButton    нажатие на кнопку понравиться
 */
@Composable
fun AnimatedFavoriteButton(
    isButtonState: MutableState<Boolean>,
    onClickHeartButton: () -> Unit,
) {

    val drawableRes by animateIntAsState(targetValue = if (isButtonState.value) R.drawable.heart_red else R.drawable.heart_grey)

    loadPicture(drawable = drawableRes).value?.let { image ->
        Image(
            bitmap = image.asImageBitmap(),
            modifier = Modifier
                .clickable(onClick = onClickHeartButton)
                .width(50.dp)
                .height(50.dp)
                .padding(10.dp),
            contentDescription = null,
        )
    }
}