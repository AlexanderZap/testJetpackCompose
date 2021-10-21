/*
 * Created 18.9.2021. Dmitriy Mertsalov
 */

package ru.zapashnii.testjetpackcompose.ui.common

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

/**
 * Анимированное появление и исчезновение контента.
 * Контент плавно разворачивается сверху.
 *
 * @param visible   видимость контента
 * @param content   контент
 */
@Composable
@ExperimentalAnimationApi
fun VisibilityAnimationComponent(
    visible: Boolean = false,
    content: @Composable () -> Unit = {},
) {
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(
            // Развернуть сверху.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Появление с начальной альфа 0f.
            initialAlpha = 0f
        ),
        exit = shrinkVertically() + fadeOut()
    ) {
        content()
    }
}