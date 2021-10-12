package ru.zapashnii.testjetpackcompose.di

import dagger.Module

/**  Dagger модуль для связывания интрефейсов с их реализациями уровня приложения */
@Module
interface AppBindsModule {
    /*
     * --------------------------------
     * Бинды для сервисов
     * --------------------------------
     */

    /*@Binds
    @Reusable
    fun provideSearchWeatherService(service: SearchWeatherService): ISearchWeatherService*/

    /*
     * --------------------------------
     * Бинды для репозиториев
     * --------------------------------
     */

/*    @Binds
    @Reusable
    fun provideSearchWeatherRepository(repository: SearchWeatherRepository): ISearchWeatherRepository*/

    /*
     * --------------------------------
     * Бинды для интеракторов (UseCase)
     * --------------------------------
     */

/*    @Binds
    @Reusable
    fun provideWeatherByCityNameUseCase(useCase: WeatherByCityNameUseCase): IWeatherByCityNameUseCase*/
}