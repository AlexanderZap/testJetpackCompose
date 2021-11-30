package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
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
@Preview
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

/**
 * Новый тулбар
 *
 * @param textTitleRes  текст в тулбаре
 * @param layoutId      префикс идентификатора элемента в его родительском элементе
 * @param onBackClick   нажатие на кнопку Назад
 */
@Preview
@Composable
fun ToolbarNew(
    textTitleRes: Int = R.string.app_name,
    layoutId: String = "toolbar",
    onBackClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .layoutId("${layoutId}Title")
                    .fillMaxWidth(),
                text = stringResource(textTitleRes),
                textAlign = TextAlign.Center
            )
        },
        backgroundColor = Color(0xffF4F5F7),
        navigationIcon = {
            IconButton(
                modifier = Modifier.layoutId("${layoutId}Button"),
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = null,
                    tint = Color(0xff1768B1)
                )
            }
        }
    )
}