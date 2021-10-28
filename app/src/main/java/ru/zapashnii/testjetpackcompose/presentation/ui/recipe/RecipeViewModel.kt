package ru.zapashnii.testjetpackcompose.presentation.ui.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class RecipeViewModel(

) : ViewModel() {

    /** Фабрика [RecipeViewModel] */
    class Factory @Inject constructor(
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RecipeViewModel(
            ) as T
        }
    }
}