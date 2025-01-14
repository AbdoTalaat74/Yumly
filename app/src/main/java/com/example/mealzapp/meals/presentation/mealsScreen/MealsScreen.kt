package com.example.mealzapp.meals.presentation.mealsScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mealzapp.meals.presentation.composables.MealCard
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.presentation.composables.EmptyScreen
import com.example.mealzapp.ui.theme.PurpleGrey80
import com.example.mealzapp.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsScreen(
    state: MealsState,
    onItemClick: (Meal) -> Unit,
    onNavigateUpClick: () -> Unit
) {
    val mealsViewModel: MealsViewModel = hiltViewModel()


    Scaffold(modifier = Modifier.fillMaxSize(), topBar =
    {
        TopAppBar(
            title = { Text(text = mealsViewModel.topAppBarTitle ?: "Meals") },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = MaterialTheme.dimens.small1),
            navigationIcon = {
                IconButton(onClick = { onNavigateUpClick() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
        )
    }
    ) { paddingValues ->
        if (state.error != null) {
            EmptyScreen(error = state.error)
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = MaterialTheme.dimens.small3,
                            start = MaterialTheme.dimens.small3,
                            end = MaterialTheme.dimens.small3,
                        ),
                ) {
                    items(state.meals.size) { index ->
                        MealCard(
                            meal = state.meals[index],
                            onClick = {
                                onItemClick(it)
                            }
                        )
                        if (index == state.meals.size - 1) {
                            mealsViewModel.loadMeals()
                        }
                    }
                }

                if (state.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = PurpleGrey80,
                            modifier = Modifier.padding(MaterialTheme.dimens.small3)
                        )
                    }
                }
            }
        }


    }


}