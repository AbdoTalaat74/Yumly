package com.example.mealzapp.meals.presentation.composables

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
fun MealCard(meal: Meal, onClick: (Meal) -> Unit, isInMainScreen: Boolean = false) {

    Card(
        shape = RoundedCornerShape(MaterialTheme.dimens.small3),
        elevation = CardDefaults.cardElevation(MaterialTheme.dimens.small1),
        modifier = Modifier
            .width(MaterialTheme.dimens.cardWidth)
            .padding(bottom = MaterialTheme.dimens.small3)
            .padding(horizontal = MaterialTheme.dimens.small1)
            .clickable { onClick(meal) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = MaterialTheme.dimens.small2)
        ) {
            Image(
                painter = rememberAsyncImagePainter(meal.strMealThumb),
                contentDescription = meal.strMeal,
                contentScale = ContentScale.Crop,  // Ensures proper cropping
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.imageSize)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))
            Text(
                text = meal.strMeal ?: "",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                maxLines = if (isInMainScreen) 1 else 10,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimens.small2)
            )
        }
    }
}

