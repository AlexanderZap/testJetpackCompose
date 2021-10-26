package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ru.zapashnii.testjetpackcompose.presentation.ui.recipe_list.FoodCategory
import ru.zapashnii.testjetpackcompose.R

/**
 * Строка поиска с чипами разных категорий
 *
 * @param query                         Параметры запроса
 * @param onQueryChanged                Изменить параметры запроса
 * @param onExecuteSearch               Выполнить поиск
 * @param categories                    Список категорий для чипов
 * @param getSelectedTabIndex           Индекс выбранной категории
 * @param selectedCategory              Выбранная категория
 * @param onSelectedCategoryChanged     Нажатие на категорию
 * @param onToggleTheme                 Нажатие на смену темы
 */
@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    categories: List<FoodCategory>,
    getSelectedTabIndex: Int,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onToggleTheme: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.surface,
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
                        onQueryChanged(it)
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
                        onExecuteSearch()
                        //скрывать клавиатуру
                        focusManager.clearFocus()
                    })
                )
                ConstraintLayout(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    val menu = createRef()
                    IconButton(
                        modifier = Modifier.constrainAs(menu) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                        onClick = onToggleTheme
                    ) {
                        Icon(
                            painterResource(if (!MaterialTheme.colors.isLight) R.drawable.ic_sun_24dp else R.drawable.ic_moon_24dp),
                            contentDescription = "Theme"
                        )
                    }
                }
            }
            ScrollableTabRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                selectedTabIndex = getSelectedTabIndex,
                edgePadding = 16.dp,
                backgroundColor = Color.White)
            {
                for (category in categories) {
                    key(category.name) {
                        FoodCategoryChip(
                            category = category.value,
                            isSelected = selectedCategory == category,
                            onSelectedCategoryChanged = { onSelectedCategoryChanged(it) },
                            onExecuteSearch = { onExecuteSearch() },
                        )
                    }
                }
            }
        }
    }
}