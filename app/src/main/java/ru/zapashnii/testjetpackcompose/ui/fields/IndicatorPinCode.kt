package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.zapashnii.testjetpackcompose.ui.theme.BackgroundIndicatorPinSwitchOf
import ru.zapashnii.testjetpackcompose.ui.theme.BackgroundIndicatorPinSwitchOn

/**
 * Индикатор ввода ПИН-кода
 *
 * @param modifier  модификатор
 * @param pinSize   размер ПИН-кода
 */
@Preview
@Composable
fun IndicatorPinCode(modifier: Modifier = Modifier, pinSize: Int = 0) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(if (pinSize >= 1) BackgroundIndicatorPinSwitchOn else BackgroundIndicatorPinSwitchOf)
        )
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(16.dp)
                .clip(CircleShape)
                .background(if (pinSize >= 2) BackgroundIndicatorPinSwitchOn else BackgroundIndicatorPinSwitchOf)
        )
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(16.dp)
                .clip(CircleShape)
                .background(if (pinSize >= 3) BackgroundIndicatorPinSwitchOn else BackgroundIndicatorPinSwitchOf)
        )
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(16.dp)
                .clip(CircleShape)
                .background(if (pinSize == 4) BackgroundIndicatorPinSwitchOn else BackgroundIndicatorPinSwitchOf)
        )
    }
}