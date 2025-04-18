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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.mealzapp.R
import com.example.mealzapp.meals.domain.getAria.getAreaModel
import com.example.mealzapp.ui.theme.dimens

@Composable
fun AreaCard(
    area: String,
    onClick: (area: String) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(MaterialTheme.dimens.small1), // Adjusted for rounded corners
        elevation = CardDefaults.cardElevation(MaterialTheme.dimens.small2), // Adjusted for elevation
        modifier = Modifier
            .padding(MaterialTheme.dimens.small1)
            .clickable {
                onClick(area)
            }
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = MaterialTheme.dimens.small1,
                vertical = MaterialTheme.dimens.small1
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = getAreaModel(area),
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_area),
                modifier = Modifier
                    .size(MaterialTheme.dimens.medium3) // Adjusted for image size
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.small1))

            Text(
                text = area,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}




