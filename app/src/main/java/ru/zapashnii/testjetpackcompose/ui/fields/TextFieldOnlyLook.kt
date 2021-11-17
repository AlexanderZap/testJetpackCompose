package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ru.zapashnii.testjetpackcompose.ui.common.Mask
import ru.zapashnii.testjetpackcompose.ui.theme.BackgroundTextField

/**
 * Кастомный TextField, который отображает текст с иконкой в конце
 *
 * @param modifier          модификатор для настройки Box
 * @param text              текст отображаемый в TextField
 * @param onClickIcon       нажатие на иконку
 * @param isNeedIcon        отображать иконку в конце
 * @param icons             иконка
 * @param mask              маска форматирования
 */
@Composable
fun TextFieldOnlyLook(
    modifier: Modifier = Modifier,
    text: String,
    onClickIcon: () -> Unit = {},
    isNeedIcon: Boolean = true,
    icons: ImageVector = Icons.Default.ContentCopy,
    mask: Mask = Mask.None,
) {
    Box(modifier = modifier
        .clip(MaterialTheme.shapes.large)
        .fillMaxWidth()
        .background(BackgroundTextField)
    ) {
        ConstraintLayout(Modifier.fillMaxWidth()) {
            val (textCVV, textWarning) = createRefs()
            BasicTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentWidth(Alignment.Start)
                    .constrainAs(textCVV) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(textWarning.start)
                        width = Dimension.fillToConstraints
                    },
                value = text,
                onValueChange = {},
                enabled = false,
                visualTransformation = mask.transformation,
                textStyle = LocalTextStyle.current,
            )
            if (isNeedIcon) {
                IconButton(
                    modifier = Modifier
                        .size(24.dp)
                        .wrapContentWidth(Alignment.End)
                        .constrainAs(textWarning) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end, 8.dp)
                            bottom.linkTo(parent.bottom)
                        },
                    onClick = onClickIcon,
                ) {
                    Icon(icons,
                        "",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TextFieldOnlyLookPreview() {
    TextFieldOnlyLook(text = "123456789")
}