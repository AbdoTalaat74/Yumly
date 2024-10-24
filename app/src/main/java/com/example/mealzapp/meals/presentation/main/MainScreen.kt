package com.example.mealzapp.meals.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mealzapp.R
import com.example.mealzapp.composables.CategoryCard
import com.example.mealzapp.composables.MealCard
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.ui.theme.PurpleGrey80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: CategoryState,
    randomMealsState: RandomMealsState,
    onClickItem: (categoryName: String) -> Unit,
    onMealClick: (Meal) -> Unit,
    onRefresh: () -> Unit,

) {
    val isRefreshing = randomMealsState.refreshState

    Scaffold(modifier = Modifier.fillMaxSize(), topBar =
    {
        TopAppBar(
            title = { Text(text = "Meals App") },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 4.dp)
        )
    }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateTopPadding()
                )
        ) {
            if (state.isLoading || randomMealsState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = PurpleGrey80)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.random_meals),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold),
                        modifier = Modifier
                            .padding(12.dp)
                    )

                    Box {
                        Icon(
                            painter = painterResource(R.drawable.refresh_icon),
                            contentDescription = "refresh",
                            Modifier
                                .alpha(if (isRefreshing) 0f else 1f) // Hidden when refreshing
                                .clickable {
                                    if (!isRefreshing) onRefresh() // Call refresh
                                }
                        )

                        CircularProgressIndicator(
                            modifier = Modifier.alpha(if (isRefreshing) 1f else 0f) // Shown when refreshing
                        )
                    }

                }


                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    items(randomMealsState.meals) { meal ->
                        MealCard(meal, onClick = {
                            onMealClick(it)
                        })
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.categories),
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(12.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    items(state.categories) { category ->
                        CategoryCard(
                            category = category,
                            onClick = { onClickItem(category.strCategory) }

                        )
                    }
                }
            }
        }
    }


}

