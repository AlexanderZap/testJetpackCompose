package ru.zapashnii.testjetpackcompose.ui.common

import androidx.compose.ui.graphics.vector.ImageVector

/** Вспомогательные классы для группировки связанных параметров */

/**
 * Вспомогательный класс для групироки связанных параметров иконки
 *
 * @property icon           иконка
 * @property onClick        нажатие на иконку
 */
class IconParams(
    val icon: ImageVector,
    val onClick: (() -> Unit),
)