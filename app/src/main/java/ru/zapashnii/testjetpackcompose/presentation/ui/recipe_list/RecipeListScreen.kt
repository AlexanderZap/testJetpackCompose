package ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RecipeListScreen(vm: RecipeListViewModel = viewModel()) {
    vm.loadData()
}