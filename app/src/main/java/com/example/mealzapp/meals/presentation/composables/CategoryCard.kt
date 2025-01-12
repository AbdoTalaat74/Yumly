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
import coil.compose.rememberAsyncImagePainter
import com.example.mealzapp.meals.data.local.Category
import com.example.mealzapp.ui.theme.dimens

@Composable
fun CategoryCard(category: Category,onClick:(Category)->Unit) {
    Card(
        shape = RoundedCornerShape(MaterialTheme.dimens.small3),
        elevation = CardDefaults.cardElevation(MaterialTheme.dimens.small1),
        modifier = Modifier
            .width(MaterialTheme.dimens.smallCardSize2)
            .padding(horizontal = MaterialTheme.dimens.small1)
            .clickable {
                onClick(category)
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = MaterialTheme.dimens.small2)
        ) {
            Image(
                painter = rememberAsyncImagePainter(category.strCategoryThumb),

                contentDescription = category.strCategory,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(MaterialTheme.dimens.imageSize)
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))
            Text(
                text = category.strCategory,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = MaterialTheme.dimens.small2)
            )
        }
    }
}
