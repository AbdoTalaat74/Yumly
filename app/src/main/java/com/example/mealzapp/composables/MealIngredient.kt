package com.example.mealzapp.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mealzapp.R
import com.example.mealzapp.ui.theme.Orange

@SuppressLint("SuspiciousIndentation")
@Composable
fun MealIngredient(
    ingredient: String?,
    measure: String?,

) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "https://www.themealdb.com/images/ingredients/$ingredient.png",
            contentDescription = ingredient,
            placeholder = painterResource(R.drawable.meal_ingredient_empty),
            error = painterResource(R.drawable.meal_ingredient_empty),
            modifier = Modifier
                .size(64.dp)
                .clip(
                    CircleShape
                )
                .border(3.dp, Orange, CircleShape)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.width(24.dp))
        if (!ingredient.isNullOrEmpty()) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = ingredient,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 24.sp)
            )
        }
        if (!measure.isNullOrBlank()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = measure,
                textAlign = TextAlign.End,
                style = TextStyle(fontSize = 16.sp, color = Orange)
            )

        }
    }

}