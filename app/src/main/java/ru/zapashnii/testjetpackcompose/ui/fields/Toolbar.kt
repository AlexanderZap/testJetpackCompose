package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import ru.zapashnii.testjetpackcompose.R
import ru.zapashnii.testjetpackcompose.ui.theme.Grey1

/**
 * Тулбар с заголовком и кнопкой Назад
 *
 * @param textTitle             заголовок тулбара
 * @param layoutId              префикс идентификатора элемента в его родительском элементе
 * @param iconImageVector       изображение навигации. По умолчанию Стрелка Назад
 * @param onBackClick           нажатие на кнопку
 */
@Composable
fun Toolbar(
    textTitle: String,
    iconImageVector: ImageVector = Icons.Filled.ArrowBack,
    layoutId: String = "toolbar",
    onBackClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.layoutId("${layoutId}Title"),
                text = textTitle,
                color = Grey1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.layoutId("${layoutId}Button"),
                onClick = onBackClick
            ) {
                Icon(imageVector = iconImageVector, contentDescription = null)
            }
        }
    )
}

/**
 * Тулбар с заголовком и кнопкой Назад
 *
 * @param textTitleRes          заголовок тулбара
 * @param layoutId              префикс идентификатора элемента в его родительском элементе
 * @param iconImageVector       изображение навигации. По умолчанию Стрелка Назад
 * @param onBackClick           нажатие на кнопку
 */
@Composable
fun Toolbar(
    textTitleRes: Int = R.string.app_name,
    layoutId: String = "toolbar",
    iconImageVector: ImageVector = Icons.Filled.ArrowBack,
    onBackClick: () -> Unit = {},
) {
    Toolbar(
        textTitle = stringResource(textTitleRes),
        layoutId = layoutId,
        onBackClick = onBackClick,
        iconImageVector = iconImageVector
    )
}