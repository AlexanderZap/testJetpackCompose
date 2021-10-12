package ru.zapashnii.testjetpackcompose.domain.repository.base

/** Реализуют репозитории, которые должны быть очищены после использования. */
interface ICleanableRepository {
    fun clear()
}