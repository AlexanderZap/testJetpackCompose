package ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.ui.fields.FoodCategoryChip
import ru.zapashnii.testjetpackcompose.ui.fields.RecipeCard

@Composable
fun RecipeListScreen(viewModel: RecipeListViewModel) {

    viewModel.loadData()

    //список рецептов
    val recipes by viewModel.recipes.observeAsState(emptyList())
    //параметры запроса
    val query by viewModel.query.observeAsState("")
    //выбранная категория
    val selectedCategory by viewModel.selectedCategory.observeAsState()

    val focusManager = LocalFocusManager.current

    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.White,
            elevation = 8.dp,
        ) {
            Column {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .padding(8.dp),
                        value = query,
                        onValueChange = {
                            viewModel.onQueryChanged(it)
                        },
                        label = {
                            Text(text = "Search")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                        ),
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                        },
                        textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                        keyboardActions = KeyboardActions(onDone = {
                            viewModel.newSearch()
                            //скрывать клавиатуру
                            focusManager.clearFocus()
                        })
                    )
                }
                ScrollableTabRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp),
                    selectedTabIndex = viewModel.getSelectedTabIndex(),
                    edgePadding = 16.dp,
                    backgroundColor = Color.White)
                {
                    for (category in getAllFoodCategories()) {
                        key(category.name) {
                            FoodCategoryChip(
                                category = category.value,
                                isSelected = selectedCategory == category,
                                onSelectedCategoryChanged = {
                                    viewModel.onSelectedCategoryChanged(it)
                                },
                                onExecuteSearch = viewModel::newSearch,
                            )
                        }
                    }
                }
            }
        }

        LazyColumn {
            itemsIndexed(
                items = recipes ?: mutableListOf()
            ) { index, recipe ->
                RecipeCard(recipe = recipe ?: Recipe(), onCardClick = {})
            }
        }
    }
}