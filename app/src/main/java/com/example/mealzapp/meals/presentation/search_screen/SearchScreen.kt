package com.example.mealzapp.meals.presentation.search_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mealzapp.meals.presentation.composables.MealCard
import com.example.mealzapp.meals.presentation.composables.SearchBar
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.presentation.composables.EmptyScreen
import com.example.mealzapp.meals.presentation.mealsScreen.MealsState
import com.example.mealzapp.ui.theme.PurpleGrey80
import com.example.mealzapp.ui.theme.dimens

@Composable
fun SearchScreen(
    mealsState: MealsState,
    navigateUp: () -> Unit,
    onItemClick: (Meal) -> Unit
) {
    val searchViewModel: SearchViewModel = hiltViewModel()
    val query by searchViewModel.query


    Scaffold(
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                SearchBar(
                    value = query,
                    onQueryChanged = {
                        searchViewModel.updateQuery(it)
                        searchViewModel.searchMeal(query)
                    },
                    onBackClick = {
                        if (query.isEmpty()){
                            navigateUp()
                        }else{
                            searchViewModel.updateQuery("")
                            searchViewModel.stopSearch()
                        }
                    },
                    onSearchClick = { searchViewModel.searchMeal(query) }
                )
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.dimens.small3),
                ) {
                    if (mealsState.meals.isNotEmpty()){
                        items(mealsState.meals.size) { index ->
                            MealCard(
                                meal = mealsState.meals[index],
                                onClick = {
                                    onItemClick(it)
                                }
                            )
                        }
                    }else {
                        return@LazyVerticalStaggeredGrid
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (mealsState.isLoading) {
                    CircularProgressIndicator(
                        color = PurpleGrey80,
                        modifier = Modifier.padding(MaterialTheme.dimens.small3)
                    )
                }else {
                    mealsState.error?.let {  error ->
                        EmptyScreen(error)
                    }

                }


            }


        }



    }

}