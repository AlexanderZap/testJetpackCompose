package ru.zapashnii.testjetpackcompose.domain.interactors.base

/** Реализуют UseCase, которые должны очищать ресурсы. */
interface IDispatchUseCase {
    /** Очистить ресурсы */
    fun dispatch()
}