package com.example.mealzapp.meals.presentation.composables


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.mealzapp.ui.theme.Orange
import com.example.mealzapp.ui.theme.dimens

@Composable
fun InfoRow(iconRes: Painter, text: String, onClick:(String)->Unit,categoryName: String) {
    Card(
        shape = RoundedCornerShape(MaterialTheme.dimens.small1),
        elevation = CardDefaults.cardElevation(MaterialTheme.dimens.small2),
        modifier = Modifier
            .padding(MaterialTheme.dimens.small1)
            .clickable { onClick(categoryName) }


    ){
        Row(
            modifier = Modifier.padding(
                horizontal = MaterialTheme.dimens.small1,
                vertical = MaterialTheme.dimens.small1
            ),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                painter = iconRes,
                contentDescription = null,
                tint = Orange,
                modifier = Modifier
                    .size(MaterialTheme.dimens.medium3) // Adjusted for image size
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.small1))

                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,

                )

        }
    }

}



