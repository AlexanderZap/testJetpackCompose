package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Смещение кадра мерцания */
private const val frameOffset: Float = 50f

/**
 * Загрузка с эффектом мерцания. По кадрам рисуем эффект мерцания с шагом [frameOffset] пока не пройдем всю карточку
 *
 * @param colors                        список цветов
 * @param gradientWidth                 смещение от начальной позиции
 * @param imageHeight                   высота карточки
 * @param padding                       внутренние отступы карточки
 */
@Composable
fun LoadingRecipeListShimmer(
    colors: List<Color>,
    gradientWidth: Float,
    imageHeight: Dp,
    padding: Dp = 16.dp,
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()) {

        val positionFrameShimmer = remember { mutableStateOf(0f) }

        val state by animateFloatAsState(targetValue = positionFrameShimmer.value)

        ShimmerRecipeCardItem(
            colors = colors,
            xShimmer = state,
            yShimmer = state,
            cardHeight = imageHeight,
            gradientWidth = gradientWidth,
            padding = padding
        )
        if (positionFrameShimmer.value <= maxWidth.value * 2.5)
            positionFrameShimmer.value += frameOffset
    }
}