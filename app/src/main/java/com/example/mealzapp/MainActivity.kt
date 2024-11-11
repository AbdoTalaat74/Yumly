package com.example.mealzapp

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
import com.example.mealzapp.meals.presentation.search_screen.SearchScreen
import com.example.mealzapp.meals.presentation.search_screen.SearchViewModel
import com.example.mealzapp.ui.theme.MealsAppTheme
import com.example.mealzapp.ui.theme.dimens
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealsAppTheme(darkTheme = isSystemInDarkTheme()) {
                MealsAroundApp()
            }
        }

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)  // 1 PM
            set(Calendar.MINUTE, 9)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        val intent = Intent(this, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

    }

    @Composable
    fun MealsAroundApp() {
        val mainViewModel: MainViewModel = hiltViewModel()
        val context = LocalContext.current
        var hasNotificationPermission by remember {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                mutableStateOf(
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                )
            } else mutableStateOf(true)
        }

        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                hasNotificationPermission = isGranted

            }
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!hasNotificationPermission) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        lateinit var searchType: String
        val navController = rememberNavController()


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

        val intentUri = intent?.data
        val mealId = intentUri?.lastPathSegment // Extract meal_id from the deep link URI

        // Pass meal_id to the NavController if a deep link is present
        LaunchedEffect(mealId) {
            mealId?.let {
                navController.navigate("meal/$it")
            }
        }

        NavHost(navController = navController, startDestination = "main") {

            composable(route = "main") {
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))

                MainScreen(
                    state = mainViewModel.categoryState.value,
                    randomMealsState = mainViewModel.randomMealsState.value,
                    ingredientsState = mainViewModel.ingredientsState.value,
                    countriesState = mainViewModel.countriesState.value,
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


}






