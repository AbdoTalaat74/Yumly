package com.example.mealzapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.mealzapp.ui.theme.dimens

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onQueryChanged: (String) -> Unit,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.dimens.small3,
                vertical = MaterialTheme.dimens.small2
            )
            .background(
                color = CardDefaults.cardColors().containerColor, // Adjust the color as needed
                shape = RoundedCornerShape(MaterialTheme.dimens.medium2)
            )

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }
            BasicTextField(
                value = value,
                onValueChange = {
                    onQueryChanged(it)
                },
                singleLine = true,
                modifier = modifier
                    .weight(1f)
                    .background(Color.Transparent),

                )
            IconButton(onClick = { onSearchClick() }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SearchBarPreview() {
    SearchBar(
        value = "Beef",
        onSearchClick = {},
        onQueryChanged = {},
        onBackClick = {}
    )
}