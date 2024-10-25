package com.example.mealzapp.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
@Composable
fun IngredientCard(ingredient: Meal, onClick: (Meal) -> Unit) {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp)
            .clickable { onClick(ingredient) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Log.e("IngredientCardImage",ingredient.strIngredient?:"Empty")
            val painter = "https://www.themealdb.com/images/ingredients/${ingredient.strIngredient}.png"
            Image(
                painter = rememberAsyncImagePainter(painter),

                contentDescription = ingredient.strIngredient,
                contentScale = ContentScale.Crop,  // Ensures proper cropping
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = ingredient.strIngredient ?: "",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
