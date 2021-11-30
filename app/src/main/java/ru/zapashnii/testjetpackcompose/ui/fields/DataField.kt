package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

/**
 * Строка с данными (Поле данных)
 *
 * @property label      название поля
 * @property value      значение поля
 */
@Preview
@Composable
fun DataField(
    label: String = "Label",
    value: String = "Value",
) {

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (labelRef, lineRef, valueRef) = createRefs()
        val barrier = createEndBarrier(labelRef)
        // Label
        Text(
            text = label,
            modifier = Modifier
                .constrainAs(labelRef) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )
        // Line
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 0f)
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .constrainAs(lineRef) {
                    start.linkTo(labelRef.end)
                    bottom.linkTo(labelRef.bottom)
                    end.linkTo(valueRef.start)
                    width = Dimension.fillToConstraints
                }
        ) {
            drawLine(
                color = Color(0xffBBBBBB),
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                pathEffect = pathEffect
            )
        }
        // Value
        Text(
            text = value,
            modifier = Modifier
                .widthIn(max = 200.dp)
                .constrainAs(valueRef) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.preferredWrapContent
                },
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold
        )
    }
}