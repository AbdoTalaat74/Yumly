package com.example.mealzapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.ui.theme.dimens

@Composable
fun IngredientCard(ingredient: Meal, onClick: (Meal) -> Unit) {

    Card(
        shape = RoundedCornerShape(MaterialTheme.dimens.small3),
        elevation = CardDefaults.cardElevation(MaterialTheme.dimens.small1),
        modifier = Modifier
            .width(MaterialTheme.dimens.smallCardSize2)
            .padding(horizontal = MaterialTheme.dimens.small1)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = MaterialTheme.dimens.small2)
                .clickable {
                    onClick(ingredient)
                }

        ) {
            val painter = "https://www.themealdb.com/images/ingredients/${ingredient.strIngredient}.png"

            Image(
                painter = rememberAsyncImagePainter(painter),

                contentDescription = ingredient.strIngredient,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(MaterialTheme.dimens.imageSize)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))
            Text(
                text = ingredient.strIngredient ?: "",
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth().padding(horizontal = MaterialTheme.dimens.small2)
            )
        }
    }
}
