package ru.zapashnii.testjetpackcompose.di

import android.app.Application
import androidx.compose.runtime.mutableStateOf

class MainApp : Application() {

    companion object {
        lateinit var instance: MainApp

    }

    lateinit var applicationComponent: ApplicationComponent

    val isDark = mutableStateOf(false)

    /** Переключить световую тему */
    fun toggleLightTheme() {
        isDark.value = !isDark.value
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent = DaggerApplicationComponent.create()
    }
}