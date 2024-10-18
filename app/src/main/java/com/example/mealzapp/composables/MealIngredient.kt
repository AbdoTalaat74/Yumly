package com.example.mealzapp.composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mealzapp.R
import com.example.mealzapp.ui.theme.Orange

@SuppressLint("SuspiciousIndentation")
@Composable
fun MealIngredient(
    ingredient: String?,
    measure: String?,
    ingredientImage: String?
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        , verticalAlignment = Alignment.CenterVertically
    ) {
        if (ingredientImage.isNullOrBlank()){
            Image(
                painter = painterResource(R.drawable.meal_ingredient_empty),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.LightGray),
                modifier = Modifier
                    .size(64.dp)
                    .clip(
                        CircleShape
                    )
                    .border(3.dp, Color.Gray, CircleShape)
                    .padding(16.dp)
            )
        } else {
            Log.e("ingredient",ingredient!!)

                Image(
                painter = rememberAsyncImagePainter("https://www.themealdb.com/images/ingredients/$ingredient.png"),
                contentDescription = ingredient,
                modifier = Modifier
                    .size(64.dp)
                    .clip(
                        CircleShape
                    )
                    .border(3.dp, Orange, CircleShape)
                    .padding(16.dp)
            )
        }

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