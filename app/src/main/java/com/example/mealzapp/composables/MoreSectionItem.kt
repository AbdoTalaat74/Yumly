package com.example.mealzapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mealzapp.ui.theme.dimens

@Composable
fun MoreSectionItem(
    modifier: Modifier = Modifier,
    text: String,
    iconRes: Int,
    onClick: () -> Unit
) {
    Card(

        shape = RoundedCornerShape(MaterialTheme.dimens.small2),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                shape = RoundedCornerShape(MaterialTheme.dimens.small2)
            )

    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .clickable { onClick() }
        ) {
            Image(
                modifier = Modifier.padding(MaterialTheme.dimens.small2),
                painter = painterResource(iconRes),
                contentDescription = text
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.small2))
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(
                        end = MaterialTheme.dimens.small3,
                        top = MaterialTheme.dimens.small2,
                        bottom = MaterialTheme.dimens.small2
                    )

            )
        }
    }



}