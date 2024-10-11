package com.example.mealzapp.meals.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mealzapp.R
import com.example.mealzapp.composables.CategoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: CategoryState
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text(text = "Meals App") },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 4.dp)
        )
    }) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                )
        ) {
            // This will place the Text at the top, above the LazyRow
            Text(
                text = stringResource(R.string.categories),
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier
                    .align(Alignment.Start) // Center the text horizontally
                    .padding(12.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp),
                contentPadding = PaddingValues(vertical = 4.dp)
            ) {
                items(state.categories) { category ->
                    CategoryCard(
                        category = category
                    )
                }
            }

            // Show loading spinner when the state is loading
            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
