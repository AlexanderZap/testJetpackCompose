package ru.zapashnii.testjetpackcompose.di

import dagger.Binds
import dagger.Module
import dagger.Reusable
import ru.zapashnii.testjetpackcompose.data.network.SearchRecipesService
import ru.zapashnii.testjetpackcompose.data.repository.SearchRecipesRepository
import ru.zapashnii.testjetpackcompose.domain.interactors.search_recipes.ISearchRecipesUseCase
import ru.zapashnii.testjetpackcompose.domain.interactors.search_recipes.SearchRecipesUseCase
import ru.zapashnii.testjetpackcompose.domain.network.ISearchRecipesService
import ru.zapashnii.testjetpackcompose.domain.repository.ISearchRecipesRepository

/**  Dagger модуль для связывания интрефейсов с их реализациями уровня приложения */
@Module
interface AppBindsModule {
    /*
     * --------------------------------
     * Бинды для сервисов
     * --------------------------------
     */

    @Binds
    @Reusable
    fun provideSearchRecipesService(service: SearchRecipesService): ISearchRecipesService

    /*
     * --------------------------------
     * Бинды для репозиториев
     * --------------------------------
     */

    @Binds
    @Reusable
    fun provideSearchRecipesRepository(repository: SearchRecipesRepository): ISearchRecipesRepository

    /*
     * --------------------------------
     * Бинды для интеракторов (UseCase)
     * --------------------------------
     */

    @Binds
    @Reusable
    fun provideSearchRecipesUseCase(useCase: SearchRecipesUseCase): ISearchRecipesUseCase
}