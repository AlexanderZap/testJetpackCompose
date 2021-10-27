package ru.zapashnii.testjetpackcompose.utils

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Если snackbar видим и идет вызов нового snackbar, то он добавляется в очередь и после скрытия первого показывается
 *
 * Данный класс удаляет первый snackbar и запускает второй. Точно так же с третьим, четвертым и т. д.
 *
 * @property scope      корутина
 */
class SnackbarController
constructor(
    private val scope: CoroutineScope,
) {

    private var snackbarJob: Job? = null

    init {
        cancelActiveJob()
    }

    fun getScope() = scope

    /**
     * Показать snackbar
     *
     * @param scaffoldState     состояние составного компонента Scaffold
     * @param message           текст, который будет отображаться в Snackbar
     * @param actionLabel       метка действия, отображаемая как кнопка в Snackbar
     */
    fun showSnackbar(
        scaffoldState: ScaffoldState,
        message: String,
        actionLabel: String,
    ) {
        if (snackbarJob == null) {
            snackbarJob = scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel
                )
                cancelActiveJob()
            }
        } else {
            cancelActiveJob()
            snackbarJob = scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel
                )
                cancelActiveJob()
            }
        }
    }

    /** Удалить работу показа snackbar */
    private fun cancelActiveJob() {
        snackbarJob?.let { job ->
            job.cancel()
            snackbarJob = Job()
        }
    }
}