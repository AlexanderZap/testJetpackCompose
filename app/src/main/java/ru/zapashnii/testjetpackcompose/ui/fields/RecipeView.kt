package ru.zapashnii.testjetpackcompose.ui.fields

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.rememberDrawablePainter
import ru.zapashnii.testjetpackcompose.R
import ru.zapashnii.testjetpackcompose.domain.model.Recipe
import ru.zapashnii.testjetpackcompose.ui.theme.Shimmer

/**
 * Детализация выбранного рецепта
 *
 * @param recipe          рецепт
 */
@Composable
fun RecipeView(
    recipe: Recipe,
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(state = scrollState)) {

        recipe.featuredImage?.let { url ->
            GlideImage(
                imageModel = url,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp),
                shimmerParams = ShimmerParams(
                    baseColor = Shimmer,
                    highlightColor = Color.White,
                    durationMillis = 700,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                failure = {
                    Image(
                        painter = rememberDrawablePainter(
                            drawable = AppCompatResources.getDrawable(
                                LocalContext.current, R.drawable.empty_plate)
                        ),
                        contentDescription = "Image recipe",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(225.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            recipe.title?.let { title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h3
                    )
                    val rank = recipe.rating.toString()
                    Text(
                        text = rank,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h5
                    )
                }
            }
            //издатель
            recipe.publisher?.let { publisher ->
                val updated = recipe.dateUpdated
                Text(
                    text = if (updated != null) {
                        "Updated $updated by $publisher"
                    } else {
                        "By $publisher"
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    style = MaterialTheme.typography.caption
                )
            }
            //описание
            recipe.description?.let { description ->
                if (description != "N/A") {
                    Text(
                        text = description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
            //список ингредиентов
            for (ingredient in recipe.ingredients) {
                Text(
                    text = ingredient,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    style = MaterialTheme.typography.body1
                )
            }
            //инструкция по приготовлению
            recipe.cookingInstructions?.let { instructions ->
                Text(
                    text = instructions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}