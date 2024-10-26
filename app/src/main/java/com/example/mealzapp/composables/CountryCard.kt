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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.domain.getAria.getAreaModel

@Composable
fun CountryCard(
    meal: Meal,
    onClick: (countryName: String) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .size(100.dp)
            .padding(horizontal = 4.dp)
            .padding(bottom = 16.dp)
            .clickable { meal.strArea?.let { onClick(it) } }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {

            Image(
                painter = rememberAsyncImagePainter(meal.strArea?.let { getAreaModel(it) }),
                contentDescription = meal.strArea,
                contentScale = ContentScale.Crop,  // Ensures proper cropping
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp)
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = meal.strArea ?: "",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
    }
}