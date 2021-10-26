package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.utils.DEFAULT_RECIPE_IMAGE
import ru.zapashnii.testjetpackcompose.utils.loadPicture

/**
 * Карточка рецепта
 *
 * @param layoutId          префикс идентификатора элемента в его родительском элементе
 * @param recipe            рецепт
 * @param onCardClick       нажатие на карточку
 */
@Composable
fun RecipeCard(
    layoutId: String = "RecipeCard",
    recipe: Recipe,
    onCardClick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .layoutId("${layoutId}Parent")
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onCardClick),
        elevation = 8.dp
    ) {
        Column {
            recipe.featuredImage?.let { url ->
                val image = loadPicture(url = url, defaultImage = DEFAULT_RECIPE_IMAGE).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "Image recipe",
                        modifier = Modifier
                            .layoutId("${layoutId}Image")
                            .fillMaxWidth()
                            .height(225.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            recipe.title?.let { title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .layoutId("${layoutId}Title")
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h4
                    )
                    Text(
                        text = recipe.rating.toString(),
                        modifier = Modifier
                            .layoutId("${layoutId}Rating")
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h5
                    )
                }
            }
        }
    }
}