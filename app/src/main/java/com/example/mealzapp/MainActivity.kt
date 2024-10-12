package com.example.mealzapp

import android.os.Bundle
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
import com.example.mealzapp.meals.presentation.main.MainScreen
import com.example.mealzapp.meals.presentation.main.MainViewModel
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
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main",modifier = modifier) {
        composable(route = "main") {
            val mainViewModel: MainViewModel = hiltViewModel()
            MainScreen(
                state = mainViewModel.state.value,
                onClickItem = { categoryName ->

                    navController.navigate("meals/$categoryName")

                }
            )
        }

        composable(route = "meals/{category_name}", arguments = listOf(
            navArgument("category_name") {
                type = NavType.StringType
            }
        )) {
            val mealsViewModel: MealsViewModel = hiltViewModel()
            MealsScreen(
                state = mealsViewModel.mealsState.value,
                onItemClick = {}
            )
        }
    }
}

