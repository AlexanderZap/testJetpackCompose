package ru.zapashnii.testjetpackcompose.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import ru.zapashnii.testjetpackcompose.R
import ru.zapashnii.testjetpackcompose.di.MainApp

/** Изображение по умолчанию */
const val DEFAULT_RECIPE_IMAGE = R.drawable.empty_plate

/**
 * Утилита загрузки изображения по url с помощью Glide
 *
 * @param url               url загружаемого изображения
 * @param defaultImage      изображение по умолчанию
 * @return                  загруженное изображение
 */
@Composable
fun loadPicture(
    url: String,
    @DrawableRes defaultImage: Int,
): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = remember { mutableStateOf(null) }

    // показывать изображение по умолчанию во время загрузки изображения
    Glide.with(MainApp.instance.applicationContext)
        .asBitmap()
        .load(defaultImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?,
            ) {
                bitmapState.value = resource
            }
        })

    // получить загруженное изображение
    Glide.with(MainApp.instance.applicationContext)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?,
            ) {
                bitmapState.value = resource
            }
        })

    return bitmapState
}