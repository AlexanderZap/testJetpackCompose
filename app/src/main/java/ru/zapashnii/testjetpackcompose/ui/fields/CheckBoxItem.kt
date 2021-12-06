package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * Чекбокс с текстом. По умолчанию размер чекбокса 24x24 dp.
 * Отступ между чекбоксом и тестом 8 dp
 *
 * @param modifier      модификатор текста
 * @param isChecked     чекбокс включен/невкдючен
 * @param checked       нажатие на чекбокс
 * @param sizeCheckBox  множитель для масштабирования размера чекбокса
 * @param text          текст в чекбоксе
 * @param textStyle         стиль текста
 */
@Composable
fun CheckBoxItem(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    checked: (Boolean) -> Unit,
    sizeCheckBox: Float = 1f,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.body1,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Checkbox(
            modifier = Modifier.scale(sizeCheckBox),
            checked = isChecked,
            onCheckedChange = { checked.invoke(it) },
        )
        Text(
            modifier = modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            text = text,
            style = textStyle
        )
    }
}

@Preview
@Composable
fun CheckBoxItemPreview() {
    Surface {
        CheckBoxItem(
            checked = {},
            text = "Данные отображены верно и мной проверены",
        )
    }
}

/**
 * Чекбокс с текстом на который можно нажать. По умолчанию размер чекбокса 24x24 dp.
 * Отступ между чекбоксом и тестом 8 dp
 *
 * @param modifier          модификатор текста
 * @param isChecked         чекбокс включен/невкдючен
 * @param checked           нажатие на чекбокс
 * @param sizeCheckBox      множитель для масштабирования размера чекбокса
 * @param clickableText     текст в чекбоксе
 * @param onClickText       нажатие на текст в чекбоксе
 * @param textStyle         стиль текста
 * @param tag               тег текста на который идет нажатие
 */
@Composable
fun CheckBoxItemClickableText(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    checked: (Boolean) -> Unit,
    sizeCheckBox: Float = 1f,
    clickableText: AnnotatedString,
    onClickText: (String) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    tag: String = "URL",
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Checkbox(
            modifier = Modifier.scale(sizeCheckBox),
            checked = isChecked,
            onCheckedChange = { checked.invoke(it) },
        )
        ClickableText(
            modifier = modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            text = clickableText,
            onClick = { offset ->
                clickableText.getStringAnnotations(tag = tag, start = offset, end = offset)
                    .firstOrNull()?.let { annotation ->
                        onClickText(annotation.item)
                    }
            },
            style = textStyle
        )
    }
}

@Preview
@Composable
fun CheckBoxItemClickableTextPreview() {
    Surface {
        CheckBoxItemClickableText(
            checked = {},
            clickableText = buildAnnotatedString {
                append("Click ")
                pushStringAnnotation(tag = "URL", annotation = "https://developer.android.com")
                withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                    append("here")
                }
                pop()
            },
            onClickText = {}
        )
    }
}