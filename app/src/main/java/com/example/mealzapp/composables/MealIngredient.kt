package com.example.mealzapp.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mealzapp.R
import com.example.mealzapp.ui.theme.Orange
import com.example.mealzapp.ui.theme.dimens

@SuppressLint("SuspiciousIndentation")
@Composable
fun MealIngredient(
    ingredient: String?,
    measure: String?,
    onClick: (ingredient: String) -> Unit

) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (ingredient != null) {
                    onClick(ingredient)
                }
            }
            .padding(MaterialTheme.dimens.small2)

        , verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "https://www.themealdb.com/images/ingredients/$ingredient.png",
            contentDescription = ingredient,
            placeholder = painterResource(R.drawable.meal_ingredient_empty),
            error = painterResource(R.drawable.meal_ingredient_empty),
            modifier = Modifier
                .size(MaterialTheme.dimens.extraLarge)
                .clip(
                    CircleShape
                )
                .border(2.dp, Orange, CircleShape)
                .padding(MaterialTheme.dimens.small2)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.dimens.small2))
        if (!ingredient.isNullOrEmpty()) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = ingredient,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        if (!measure.isNullOrBlank()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = measure,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )

        }
    }

}