package com.example.mealzapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealzapp.meals.presentation.full_image.FullImageViewModel
import com.example.mealzapp.meals.presentation.main.MainScreen
import com.example.mealzapp.meals.presentation.main.MainViewModel
import com.example.mealzapp.meals.presentation.meal_details.MealDetailsViewModel
import com.example.mealzapp.meals.presentation.full_image.FullImageScreen
import com.example.mealzapp.meals.presentation.meal_details.MealScreen
import com.example.mealzapp.meals.presentation.mealsScreen.MealsScreen
import com.example.mealzapp.meals.presentation.mealsScreen.MealsViewModel
import com.example.mealzapp.ui.theme.MealsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()


        setContent {
            MealsAppTheme(darkTheme = isSystemInDarkTheme()) {

                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(
                        title = { Text(text = "Meals App") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(elevation = 4.dp)
                    )
                }) { paddingValues ->
                    MealsAroundApp(
                        modifier = Modifier.padding(
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()
                        )
                    )
                }


            }
        }
    }
}

@Composable
fun MealsAroundApp(modifier: Modifier) {
    lateinit var searchType: String
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "main", modifier = modifier) {
        composable(route = "main") {
            searchType = "category"
            MainScreen(
                state = mainViewModel.state.value,
                onClickItem = { categoryName ->

                    navController.navigate("meals/$categoryName/$searchType")

                }
            )
        }

        composable(route = "meals/{filter_key}/{search_type}", arguments = listOf(
            navArgument("filter_key") { type = NavType.StringType },
            navArgument("search_type") { NavType.StringType }
        )) {
            val mealsViewModel: MealsViewModel = hiltViewModel()
            MealsScreen(
                state = mealsViewModel.mealsState.value,
                onItemClick = {
                    navController.navigate(route = "meal/${it.idMeal}")
                    Log.i("MealIdNavigation", it.idMeal.toString())
                }
            )
        }

        composable(route = "meal/{meal_id}", arguments = listOf(
            navArgument("meal_id") {
                type = NavType.IntType
            }
        )
        ) {
            val mealDetailsViewModel: MealDetailsViewModel = hiltViewModel()

            val categoryName = mealDetailsViewModel.mealState.value.meal?.strCategory

            MealScreen(mealDetailsViewModel.mealState.value, onCategoryClick = {
                searchType = "category"
                navController.navigate(route = "meals/$categoryName/$searchType")
            },
                onAreaClick = {
                    searchType = "area"
                    navController.navigate(route = "meals/$it/$searchType")
                }
                , onIngredientClick = {
                    searchType = "ingredient"
                    navController.navigate(route = "meals/$it/$searchType")
                },
                onImageClick = {
                    navController.navigate(route = "mealImage/${Uri.encode(it)}")
                }
            )
        }

        composable(route = "mealImage/{image_url}", arguments = listOf(
            navArgument(name = "image_url"){
                type = NavType.StringType
            }
        ))
        {
            val fullImageViewModel: FullImageViewModel = hiltViewModel()
            fullImageViewModel.imageUrl?.let { it1 -> FullImageScreen(it1) }
        }
    }
}

