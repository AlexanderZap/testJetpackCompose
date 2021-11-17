/*
 * Created 01.09.2021. Dmitriy Mertsalov
 */

package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import ru.zapashnii.testjetpackcompose.ui.common.PostfixTransformation
import ru.zapashnii.testjetpackcompose.utils.Utils
import ru.zapashnii.testjetpackcompose.utils.formatToMoney
import ru.zapashnii.testjetpackcompose.utils.removeSpaces
import ru.zapashnii.testjetpackcompose.R

/**
 * Поле ввода суммы.
 * Преобразует введённый текст с числом в текст с числом и символ валюты через пробел
 *
 * @param modifier
 * @param layoutId          префикс идентификатора элемента в его родительском элементе
 * @param labelResId        текст заголовка
 * @param currency          валюта
 * @param value             отображаемое значение
 * @param onDone            действие при нажатии Done на клавиатуре
 * @param onValueChange     функция возврата изменённого значения
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputSumField(
    modifier: Modifier = Modifier,
    layoutId: String = "inputSumField",
    labelResId: Int = R.string.sum,
    currency: String = "RUB",
    value: String = "",
    error: String? = null,
    onDone: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
) {
    Surface(Modifier.layoutId("${layoutId}Root")) {
        val localFocusManager = LocalFocusManager.current
        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current
        var displayValue by remember { mutableStateOf(value) }

        Column(Modifier.fillMaxWidth()) {
            // Поле ввода суммы
            TextField(
                modifier = modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth()
                    .clickable { focusRequester.requestFocus() }
                    .layoutId("${layoutId}TextField"),
                value = displayValue,
                onValueChange = {
                    displayValue = it.removeExcessSymbolsInAmountString()
                        .replace(".", ",")
                    onValueChange(displayValue)
                },
                label = { Text(stringResource(labelResId)) },
                singleLine = true,
                isError = error != null,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Спрятать клавиатуру при потере фокуса
                        keyboardController?.hide()
                        localFocusManager.clearFocus()
                        displayValue = displayValue.formatToMoney() ?: ""
                        onDone.invoke()
                    }
                ),
                visualTransformation = PostfixTransformation(" ${Utils.getCurrency(currency)}")
            )
            if (error != null) {
                // Текст ошибки
                Text(
                    modifier = Modifier.layoutId("${layoutId}Error"),
                    text = error,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.error
                )
            }
        }
    }
}

/**
 * Преобразовать строку в числовую строку
 */
fun String.removeExcessSymbolsInAmountString(): String {

    // 1 - Если цифра начинается больше чем с одного нуля, то подставляем один ноль
    val noManyZeroStart = if (this.startsWith("00")) this.replace("00", "0") else this

    // 2 - Если первый символ 0, а второй не ноль и не разделитель, то заменить ноль на второй символ
    val noStartZero = if (
        noManyZeroStart.length >= 2
        && noManyZeroStart.startsWith("0")
        && (noManyZeroStart[1] != '.' && noManyZeroStart[1] != ',')
    ) {
        noManyZeroStart[1].toString()
    } else {
        noManyZeroStart
    }

    // 3 - Убираем пробелы
    val noSpacesText = noStartZero.removeSpaces()

    // 4 - Находим индекс последней запятой или точки
    val lastDecimalSeparatorIndex = noSpacesText.indexOfLast { it == '.' || it == ',' }

    // 5 - Удаляем все запятые и точки, кроме последней
    val removedRedundantSymbolsText =
        noSpacesText.filterIndexed { index, c -> !((c == ',' || c == '.') && index != lastDecimalSeparatorIndex) }

    // 6 - Заменяем запятую на точку и удаляем все что не цифра или точка
    val onlyDigitsAndDotsText = removedRedundantSymbolsText
        .replace(',', '.')
        .replace("[^0-9.]".toRegex(), "")

    // 7 - Удаляем лишние символы после запятой, если есть
    val trimDigits = if (onlyDigitsAndDotsText.split(".").getOrNull(1)?.length ?: 0 > 2) {
        onlyDigitsAndDotsText.substring(0..onlyDigitsAndDotsText.length - 2)
    } else {
        onlyDigitsAndDotsText
    }

    return trimDigits
}