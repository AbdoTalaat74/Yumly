package com.example.mealzapp.composables


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mealzapp.R
import com.example.mealzapp.meals.domain.getAria.getAreaModel

@Composable
fun AreaCard(area: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(8.dp)

    ){
        Row(
            modifier = Modifier.height(30.dp).width(150.dp).padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            AsyncImage(
                model = getAreaModel(area),
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_area),
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = area,
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }

}



