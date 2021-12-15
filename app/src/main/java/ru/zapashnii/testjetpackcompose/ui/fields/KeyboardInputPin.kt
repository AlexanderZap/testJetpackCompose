package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import ru.zapashnii.testjetpackcompose.R

/**
 * Клавиатура для ввода ПИН-кода
 *
 * @param onClickKeyboard   нажатие на клавиатуру
 * @param pinSize           размер ПИН-кода
 */
@Preview
@Composable
fun KeyboardInputPin(
    onClickKeyboard: (String) -> Unit = {},
    pinSize: Int = 0,
) {
    Column(Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        ConstraintLayout(Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
        ) {
            val (
                button1,
                button2,
                button3,
                button4,
                button5,
                button6,
                button7,
                button8,
                button9,
                button0,
                buttonClear,
            ) = createRefs()
            Text(
                modifier = Modifier
                    .constrainAs(button1) {
                        top.linkTo(parent.top, 24.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
                    .clip(CircleShape)
                    .clickable { onClickKeyboard("1") },
                fontSize = 40.sp,
                letterSpacing = 32.sp,
                textAlign = TextAlign.Center,
                text = "1"
            )
            Text(
                modifier = Modifier
                    .constrainAs(button2) {
                        top.linkTo(parent.top, 24.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }
                    .clip(CircleShape)
                    .clickable { onClickKeyboard("2") },
                fontSize = 40.sp,
                letterSpacing = 32.sp,
                textAlign = TextAlign.Center,
                text = "2",
            )
            Text(
                modifier = Modifier
                    .constrainAs(button3) {
                        top.linkTo(parent.top, 24.dp)
                        end.linkTo(parent.end, 16.dp)
                    }
                    .clip(CircleShape)
                    .clickable { onClickKeyboard("3") },
                fontSize = 40.sp,
                letterSpacing = 32.sp,
                textAlign = TextAlign.Center,
                text = "3",
            )
            Text(
                modifier = Modifier
                    .constrainAs(button4) {
                        top.linkTo(button1.bottom, 24.dp)
                        start.linkTo(button1.start)
                        end.linkTo(button1.end)
                    }
                    .clip(CircleShape)
                    .clickable { onClickKeyboard("4") },
                fontSize = 40.sp,
                letterSpacing = 32.sp,
                textAlign = TextAlign.Center,
                text = "4",
            )
            Text(
                modifier = Modifier
                    .constrainAs(button5) {
                        top.linkTo(button2.bottom, 24.dp)
                        start.linkTo(button2.start)
                        end.linkTo(button2.end)
                    }
                    .clip(CircleShape)
                    .clickable { onClickKeyboard("5") },
                fontSize = 40.sp,
                letterSpacing = 32.sp,
                textAlign = TextAlign.Center,
                text = "5",
            )
            Text(
                modifier = Modifier
                    .constrainAs(button6) {
                        top.linkTo(button3.bottom, 24.dp)
                        start.linkTo(button3.start)
                        end.linkTo(button3.end)
                    }
                    .clip(CircleShape)
                    .clickable { onClickKeyboard("6") },
                fontSize = 40.sp,
                letterSpacing = 32.sp,
                textAlign = TextAlign.Center,
                text = "6",
            )
            Text(
                modifier = Modifier
                    .constrainAs(button7) {
                        top.linkTo(button4.bottom, 24.dp)
                        start.linkTo(button4.start)
                        end.linkTo(button4.end)
                    }
                    .clip(CircleShape)
                    .clickable { onClickKeyboard("7") },
                fontSize = 40.sp,
                letterSpacing = 32.sp,
                textAlign = TextAlign.Center,
                text = "7",
            )
            Text(
                modifier = Modifier
                    .constrainAs(button8) {
                        top.linkTo(button5.bottom, 24.dp)
                        start.linkTo(button5.start)
                        end.linkTo(button5.end)
                    }
                    .clip(CircleShape)
                    .clickable { onClickKeyboard("8") },
                fontSize = 40.sp,
                letterSpacing = 32.sp,
                textAlign = TextAlign.Center,
                text = "8",
            )
            Text(
                modifier = Modifier
                    .constrainAs(button9) {
                        top.linkTo(button6.bottom, 24.dp)
                        start.linkTo(button6.start)
                        end.linkTo(button6.end)
                    }
                    .clip(CircleShape)
                    .clickable { onClickKeyboard("9") },
                fontSize = 40.sp,
                letterSpacing = 32.sp,
                textAlign = TextAlign.Center,
                text = "9",
            )
            Text(
                modifier = Modifier
                    .constrainAs(button0) {
                        top.linkTo(button8.bottom, 24.dp)
                        start.linkTo(button8.start)
                        end.linkTo(button8.end)
                    }
                    .clip(CircleShape)
                    .clickable { onClickKeyboard("0") },
                fontSize = 40.sp,
                letterSpacing = 32.sp,
                textAlign = TextAlign.Center,
                text = "0",
            )
            if (pinSize != 0)
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(44.dp)
                        .constrainAs(buttonClear) {
                            top.linkTo(button0.top)
                            start.linkTo(button9.start)
                            end.linkTo(button9.end)
                            bottom.linkTo(button0.bottom)
                        },
                    onClick = { onClickKeyboard("") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_backwards),
                        tint = Color.Unspecified,
                        contentDescription = null
                    )
                }
        }
    }
}