//package com.example.mealzapp.meals.presentation.meal_details
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import com.example.mealzapp.ui.theme.dimens
//import com.google.accompanist.pager.ExperimentalPagerApi
//import com.google.accompanist.pager.rememberPagerState
//
//@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
//@Composable
//fun MealScreenFix(
//    mealState: MealState,
//    onCategoryClick: (String) -> Unit,
//    onAreaClick: (String) -> Unit,
//    onIngredientClick: (String) -> Unit,
//    onImageClick: (String) -> Unit,
//    onNavigateUpClick: () -> Unit
//) {
//    val pageState = rememberPagerState()
//    val scope = rememberCoroutineScope()
//    val tabTitles = listOf("Ingredients", "Instructions", "More")
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        topBar = {
//            TopAppBar(
//                title = { Text(mealState.meal?.strMeal ?: "Meals App") },
//                navigationIcon = {
//                    IconButton(onClick = { onNavigateUpClick() }) {
//                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .shadow(elevation = MaterialTheme.dimens.small2)
//            )
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//        ) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f), // This makes LazyColumn take up remaining space
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                item {
//                    MealInfo(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight(0.4f),
//                        mealState, onImageClick, onCategoryClick, onAreaClick)
//                }
//
//            }
//
//            MealInstructions(
//                pageState = pageState,
//                tabTitles = tabTitles,
//                scope = scope,
//                mealState = mealState,
//                onIngredientClick = onIngredientClick,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(0.6f) // Adjust to occupy 40% of the screen height, or use another appropriate height
//            )
//        }
//
//    }
//}