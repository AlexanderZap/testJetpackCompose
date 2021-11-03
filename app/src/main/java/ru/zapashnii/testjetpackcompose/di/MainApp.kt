package ru.zapashnii.testjetpackcompose.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf

class MainApp : Application() {

    companion object {
        lateinit var prefs: PreferencesTheme
        lateinit var instance: MainApp
    }

    lateinit var applicationComponent: ApplicationComponent

    /** Текущаая тема. Если True, то темная иначе светлая */
    val isDark = mutableStateOf(false)

    /** Переключить световую тему */
    fun toggleLightTheme() {
        //сохраняем изменения темы
        prefs.isDarkPrefs = !isDark.value
        isDark.value = !isDark.value
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent = DaggerApplicationComponent.create()
        prefs = PreferencesTheme(applicationContext)
        //загружаем сохранненую тему
        isDark.value = prefs.isDarkPrefs
    }
}

private const val APP_PREF_INT_EXAMPLE = "intExamplePref"

class PreferencesTheme(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(APP_PREF_INT_EXAMPLE, Context.MODE_PRIVATE)

    /** Текущаая тема. Если True, то темная иначе светлая */
    var isDarkPrefs: Boolean
        get() = preferences.getBoolean(APP_PREF_INT_EXAMPLE, false)
        set(value) = preferences.edit().putBoolean(APP_PREF_INT_EXAMPLE, value).apply()

    /** Переключить тему */
/*    fun toggleLightTheme() {
        isDarkPrefs.value = !isDarkPrefs.value
    }*/
}