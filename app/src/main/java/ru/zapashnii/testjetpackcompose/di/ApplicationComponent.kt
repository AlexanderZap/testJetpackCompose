package ru.zapashnii.testjetpackcompose.di

import dagger.Component
import javax.inject.Singleton

/** Граф зависимостей */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, AppBindsModule::class])
interface ApplicationComponent {

}