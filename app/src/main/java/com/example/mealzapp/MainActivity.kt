package com.example.mealzapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.presentation.composables.EmptyScreen
import com.example.mealzapp.meals.presentation.full_image.FullImageViewModel
import com.example.mealzapp.meals.presentation.main.MainScreen
import com.example.mealzapp.meals.presentation.main.MainViewModel
import com.example.mealzapp.meals.presentation.meal_details.MealDetailsViewModel
import com.example.mealzapp.meals.presentation.full_image.FullImageScreen
import com.example.mealzapp.meals.presentation.meal_details.MealScreen
import com.example.mealzapp.meals.presentation.mealsScreen.MealsScreen
import com.example.mealzapp.meals.presentation.mealsScreen.MealsViewModel
import com.example.mealzapp.meals.presentation.search_screen.SearchScreen
import com.example.mealzapp.meals.presentation.search_screen.SearchViewModel
import com.example.mealzapp.ui.theme.MealsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealsAppTheme(darkTheme = isSystemInDarkTheme()) {
                val mainViewModel: MainViewModel = hiltViewModel()
                val connectionState by mainViewModel.connectionState.collectAsState()
                Log.e("CategoryStateError",connectionState.toString())
                if (connectionState){
                    MealsAroundApp()
                }else{
                    EmptyScreen(error = NetworkError.NO_INTERNET_CONNECTION)
                }
            }
        }
    }
}

@Composable
fun MealsAroundApp() {
    lateinit var searchType: String
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()
    val categoryState by mainViewModel.categoryState.collectAsState()
    val randomMealsState by mainViewModel.randomMealsState.collectAsState()
    val ingredientsState by mainViewModel.ingredientsState.collectAsState()
    val countriesState by mainViewModel.countriesState.collectAsState()

    val systemUiController = rememberSystemUiController()
    if (isSystemInDarkTheme()) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }

    NavHost(navController = navController, startDestination = "main") {
        composable(route = "main") {
            MainScreen(
                categoryState = categoryState,
                randomMealsState = randomMealsState,
                ingredientsState = ingredientsState,
                countriesState = countriesState,
                onIngredientClick = { ingredientName ->
                    searchType = "ingredient"
                    navController.navigate(route = "meals/$ingredientName/$searchType")
                },
                onCategoryClick = { categoryName ->
                    searchType = "category"
                    navController.navigate("meals/$categoryName/$searchType")

                },
                onMealClick = { meal ->
                    navController.navigate(route = "meal/${meal.idMeal}")
                },
                onRefresh = {
                    mainViewModel.refreshRandomMeals()
                },
                onCountryClick = { countryName ->
                    searchType = "area"
                    navController.navigate(route = "meals/$countryName/$searchType")
                },
                onSearchClick = {
                    navController.navigate(route = "search")
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
                },
                onNavigateUpClick = { navController.navigateUp() }
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
                }, onIngredientClick = {
                    searchType = "ingredient"
                    navController.navigate(route = "meals/$it/$searchType")
                },
                onImageClick = {
                    navController.navigate(route = "mealImage/${Uri.encode(it)}")
                },
                onNavigateUpClick = {
                    navController.navigateUp()
                }

            )
        }

        composable(route = "mealImage/{image_url}", arguments = listOf(
            navArgument(name = "image_url") {
                type = NavType.StringType
            }
        ))
        {
            val fullImageViewModel: FullImageViewModel = hiltViewModel()
            fullImageViewModel.imageUrl?.let { it1 -> FullImageScreen(it1) }
        }

        composable(route = "search") {
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                mealsState = searchViewModel.mealsState.value,
                navigateUp = {
                    navController.navigateUp()
                },
                onItemClick = {
                    navController.navigate("meal/${it.idMeal}")
                }

            )
        }
    }
}

