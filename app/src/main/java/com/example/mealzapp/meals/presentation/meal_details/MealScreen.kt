package com.example.mealzapp.meals.presentation.meal_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mealzapp.R
import com.example.mealzapp.composables.InfoRow
import com.example.mealzapp.composables.MealIngredient
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.data.local.getIngredientsList
import com.example.mealzapp.ui.theme.Brown
import com.example.mealzapp.ui.theme.Orange
import com.example.mealzapp.utils.getFlagDrawable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MealScreen(mealState: MealState) {
    val pageState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val tabTitles = listOf("Ingredients", "Instructions", "More")

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberAsyncImagePainter(mealState.meal?.strMealThumb),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
        ) {

            mealState.meal?.strCategory?.let {
                InfoRow(
                    iconRes = painterResource(R.drawable.category),
                    text = it,
                    tint = Brown
                )
            }

            val mealArea = mealState.meal?.strArea ?: "Unknown"
            val flagRes = getFlagDrawable(mealState.meal?.strArea ?: "Unknown")

            val painter = painterResource(flagRes)


            InfoRow(iconRes = painter, text = mealArea)


        }

        Spacer(modifier = Modifier.height(24.dp))


        ScrollableTabRow(
            selectedTabIndex = pageState.currentPage,
            containerColor = Color.Unspecified,
            contentColor = Orange
        ) {
            tabTitles.forEachIndexed { index, title ->

                Tab(
                    selectedContentColor = Orange,
                    unselectedContentColor = Color.Gray,
                    modifier = Modifier.fillMaxWidth(),
                    text = {
                        Text(
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth(),
                            text = title
                        )
                    },
                    selected = pageState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pageState.scrollToPage(index)
                        }
                    }
                )
            }
        }








        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            count = tabTitles.size,
            state = pageState,
        ) { page ->
            when (page) {
                0 -> mealState.meal?.let { IngredientsSection(it) }
                1 -> InstructionsSection()
                2 -> MoreSection()
            }
        }
    }

}

@Composable
fun IngredientsSection(meal: Meal) {
    val ingredients = meal.getIngredientsList()
    if (ingredients.isNotEmpty()) {
        LazyColumn {
            items(ingredients.size) { index ->
                MealIngredient(
                    ingredient = ingredients[index].first,
                    measure = ingredients[index].second,
                    ingredientImage =ingredients[index].first
                )
            }
        }
    }


}

@Composable
fun InstructionsSection() {
    Text("InstructionsSection")
}

@Composable
fun MoreSection() {
    Text("MoreSection")
}


val sampleMeal = Meal(
    idMeal = "52772",
    strMeal = "Teriyaki Chicken Casserole",
    strCategory = "Chicken",
    strArea = "Japanese",
    strInstructions = "Preheat oven to 350°F. Combine ingredients and bake.",
    strMealThumb = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
    strTags = "Meat,Casserole",
    strYoutube = "https://www.youtube.com/watch?v=4aZr5hZXP_s",
    strIngredient1 = "Soy",
    strIngredient2 = "Water",
    strIngredient3 = "Brown Sugar",
    strIngredient4 = "Ground Ginger",
    strIngredient5 = "Minced Garlic",
    strMeasure1 = "3/4 cup",
    strMeasure2 = "1/2 cup",
    strMeasure3 = "1/4 cup",
    strMeasure4 = "1/2 tsp",
    strMeasure5 = "4 tbsp"
)

val sampleState = MealState(
    meal = sampleMeal, isLoading = false, error = null
)


@Preview(showSystemUi = true)
@Composable
fun MealScreenPreview() {

    MealScreen(sampleState)
}

