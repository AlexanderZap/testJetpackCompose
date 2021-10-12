package ru.zapashnii.testjetpackcompose.utils

import android.content.Context
import ru.zapashnii.testjetpackcompose.di.ApplicationComponent
import ru.zapashnii.testjetpackcompose.di.MainApp

/**
 * Специальное расширение для получения AppComponent в любом месте, где у вас есть доступ к Context.
 * Позволяет избегать статического хранения ссылки на ваш [Application] класс
 */
val Context.appComponent: ApplicationComponent
    get() = when (this) {
        is MainApp -> applicationComponent
        else -> applicationContext.appComponent
    }