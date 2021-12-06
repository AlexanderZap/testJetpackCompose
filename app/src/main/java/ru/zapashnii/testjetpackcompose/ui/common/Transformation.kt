package ru.zapashnii.testjetpackcompose.ui.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Маска для текста
 *
 * @property transformation трансформатор текста
 */
enum class Mask(val transformation: VisualTransformation) {
    /** Маска без преобразования */
    None(VisualTransformation { text ->
        TransformedText(text, OffsetMapping.Identity)
    }),

    /** Маска для номера телефона */
    Phone(PhoneNumberTransformation()),

    /** Маска для номера карты */
    Card(CardNumberTransformation())
}

/**
 * Визуальная трансформация текста в текст с суфиксом для [TextField]
 *
 * Пример:
 *      текст = "Hello"
 *      суфикс = " World!"
 *      результат "Hello World!"
 *
 * @property postfix    суфикс
 */
class PostfixTransformation(private val postfix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return PostfixFilter(text, postfix)
    }
}

/**
 * Фибрика [TransformedText] подстановки суфикса в текст
 * !! Для пустого текста суфикс - пустая строка
 *
 * @param annotatedString   обрабатываемй текст
 * @param postfix           суфикс
 * @return                  трансформированный текст
 */
fun PostfixFilter(annotatedString: AnnotatedString, postfix: String): TransformedText {
    val text = annotatedString.text

    val postfixIfNeed = if (text.isEmpty()) "" else postfix

    val out = "$text$postfixIfNeed"

    val offsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset in 0..text.length) return offset
            return if (offset == 0) offset else text.length
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset in 0..text.length) return offset
            if (offset > text.length) return text.length
            return if (offset == 0) offset else text.length - postfixIfNeed.length
        }
    }

    return TransformedText(AnnotatedString(out), offsetTranslator)
}
/**---------------------------------------------------------------------------------------------------------*/


/**
 * Трансформация номера телефона
 *
 * Пример:
 *     номер телефона = 79189448746
 *     результат = +7(918) 944-87-46
 *
 */
class PhoneNumberTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return phoneNumFilter(text)
    }
}

fun phoneNumFilter(text: AnnotatedString): TransformedText {

    // +X(XXX)_XXX_XX_XX
    val trimmed = if (text.text.length >= 11) text.text.substring(0..10) else text.text
    var out = ""
    for (i in trimmed.indices) {
        if (i == 0) out += "+"
        if (i == 1) out += "("
        out += trimmed[i]
        if (i == 3) out += ") "
        if (i == 6 || i == 8) out += "-"

    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 0) return offset
            if (offset <= 1) return offset + 1
            if (offset <= 3) return offset + 2
            if (offset <= 7) return offset + 4
            if (offset <= 9) return offset + 5
            if (offset <= 11) return offset + 6
            return 17

        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 0) return offset
            if (offset <= 2) return offset - 1
            if (offset <= 7) return offset - 2
            if (offset <= 12) return offset - 4
            if (offset <= 15) return offset - 5
            if (offset <= 18) return offset - 6
            return 11
        }
    }

    return TransformedText(AnnotatedString(out), phoneNumberOffsetTranslator)
}
/**---------------------------------------------------------------------------------------------------------*/

/**
 * Трансформация номера карты
 *
 * Пример:
 *     номер карты = 1234123412341234
 *     результат = 1234 1234 1234 1234
 *
 */
class CardNumberTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return cardNumFilter(text)
    }
}

fun cardNumFilter(text: AnnotatedString): TransformedText {

    //XXXX XXXX XXXX XXXX
    val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 15) out += " "
    }

    val cardNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 16) return offset + 3
            return 19
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - 1
            if (offset <= 14) return offset - 2
            if (offset <= 19) return offset - 3
            return 16
        }
    }
    return TransformedText(AnnotatedString(out), cardNumberOffsetTranslator)
}

/**
 * Трансформация номера телефона
 *
 * Пример:
 *     номер телефона = 9189448746
 *     результат = +7(918) 944 87 46
 *
 */
class TransformationPhoneNumber : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return filterPhoneNum(text)
    }
}

fun filterPhoneNum(text: AnnotatedString): TransformedText {

    // +7(XXX)_XXX_XX_XX
    val trimmed = if (text.text.length >= 10) text.text.substring(0..9) else text.text
    var out = ""
    for (i in trimmed.indices) {
        if (i == 0) out += "+7("
        if (i == 3) out += ") "
        if (i == 6 || i == 8) out += " "
        out += trimmed[i]
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 0) return offset
            if (offset <= 3) return offset + 3
            if (offset <= 6) return offset + 5
            if (offset <= 8) return offset + 6
            if (offset <= 10) return offset + 7
            return 17

        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 0) return offset
            if (offset <= 6) return offset - 3
            if (offset <= 11) return offset - 5
            if (offset <= 14) return offset - 6
            if (offset <= 18) return offset - 7
            return 10
        }
    }

    return TransformedText(AnnotatedString(out), phoneNumberOffsetTranslator)
}
/**---------------------------------------------------------------------------------------------------------*/