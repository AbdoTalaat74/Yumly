package com.example.mealzapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberAsyncImagePainter
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.domain.getAria.getAreaModel
import com.example.mealzapp.ui.theme.dimens

@Composable
fun CountryCard(
    meal: Meal,
    onClick: (countryName: String) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(MaterialTheme.dimens.small1),
        modifier = Modifier
            .size(MaterialTheme.dimens.smallCardSize)
            .padding(horizontal = MaterialTheme.dimens.small1)
            .padding(bottom = MaterialTheme.dimens.small3)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { meal.strArea?.let { onClick(it) } }

        ) {

            Image(
                painter = rememberAsyncImagePainter(meal.strArea?.let { getAreaModel(it) }),
                contentDescription = meal.strArea,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(MaterialTheme.dimens.large)
                    .padding(
                        top = MaterialTheme.dimens.small2,
                        start = MaterialTheme.dimens.small2,
                        end = MaterialTheme.dimens.small2
                    )
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))
            Text(
                text = meal.strArea ?: "",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}