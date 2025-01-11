package com.example.mealzapp.meals.data

import com.example.mealzapp.meals.core.NetworkError
import com.example.mealzapp.meals.data.local.Meal
import com.example.mealzapp.meals.data.remote.CategoryResponse
import com.example.mealzapp.meals.data.remote.MealResponse
import com.example.mealzapp.meals.data.remote.MealsApiService
import com.example.mealzapp.meals.data.remote.MealsResponse
import com.example.mealzapp.meals.domain.util.Result
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsRepository @Inject constructor(
    private val apiService: MealsApiService
) {

    suspend fun getCategories():Result<CategoryResponse, NetworkError>{
        return try {
            val categoriesResult = apiService.getCategories()
            when(categoriesResult.code()){
                in 200..299 -> {
                    try {
                        val body = categoriesResult.body()
                        if (body != null) {
                            Result.Success(body)
                        } else {
                            Result.Error(NetworkError.SERIALIZATION)
                        }
                    } catch (e: Exception) {
                        Result.Error(NetworkError.SERIALIZATION)
                    }
                }
                408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)

                429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
                in 500 .. 599 -> Result.Error(NetworkError.SERVER_ERROR)

                else -> Result.Error(NetworkError.UNKNOWN_ERROR)
            }

        }catch (e: IOException) {
             Result.Error(NetworkError.NO_INTERNET_CONNECTION)
        } catch (e: SocketTimeoutException) {
             Result.Error(NetworkError.REQUEST_TIMEOUT)
        }
        catch (e:Exception){
            Result.Error(NetworkError.UNKNOWN_ERROR)
        }

    }
    suspend fun getMealsByCategory(categoryName: String, offset: Int, limit: Int): List<Meal> {
        val allMeals = apiService.getMealsByCategory(categoryName).meals
        return allMeals.drop(offset).take(limit)
    }

    suspend fun getMealDetails(mealId: Int) = apiService.getMealDetails(mealId)

    suspend fun getMealsByArea(areaName: String, offset: Int, limit: Int): List<Meal> {
        val allMeals = apiService.getMealsByArea(areaName).meals
        return allMeals.drop(offset).take(limit)
    }

    suspend fun getMealsByIngredient(ingredient: String, offset: Int, limit: Int): List<Meal> {
        val allMeals = apiService.getMealsByIngredient(ingredient).meals
        return allMeals.drop(offset).take(limit)
    }

    suspend fun getRandomMeal(): Result<MealResponse,NetworkError> {
        return try {
            val randomMealResult = apiService.getRandomMeal()
            when(randomMealResult.code()){
                in 200..299 -> {
                    try {
                        val body = randomMealResult.body()
                        if (body != null) {
                            Result.Success(body)
                        } else {
                            Result.Error(NetworkError.SERIALIZATION)
                        }
                    } catch (e: Exception) {
                        Result.Error(NetworkError.SERIALIZATION)
                    }
                }
                408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)

                429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
                in 500 .. 599 -> Result.Error(NetworkError.SERVER_ERROR)

                else -> Result.Error(NetworkError.UNKNOWN_ERROR)
            }
        }catch (e: IOException) {
            Result.Error(NetworkError.NO_INTERNET_CONNECTION)
        } catch (e: SocketTimeoutException) {
            Result.Error(NetworkError.REQUEST_TIMEOUT)
        }
        catch (e:Exception){
            Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }

    suspend fun getIngredients(): Result<MealsResponse,NetworkError> {
        return try {
            val ingredientsResult = apiService.getIngredients()
            when(ingredientsResult.code()){
                in 200..299 -> {
                    try {
                        val body = ingredientsResult.body()
                        if (body != null) {
                            Result.Success(body)
                        } else {
                            Result.Error(NetworkError.SERIALIZATION)
                        }
                    } catch (e: Exception) {
                        Result.Error(NetworkError.SERIALIZATION)
                    }
                }
                408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)

                429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
                in 500 .. 599 -> Result.Error(NetworkError.SERVER_ERROR)

                else -> Result.Error(NetworkError.UNKNOWN_ERROR)
            }
        }catch (e: IOException) {
            Result.Error(NetworkError.NO_INTERNET_CONNECTION)
        } catch (e: SocketTimeoutException) {
            Result.Error(NetworkError.REQUEST_TIMEOUT)
        }
        catch (e:Exception){
            Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }

    suspend fun getCountries():Result<MealsResponse,NetworkError>{
        return try {
            val countriesResult = apiService.getCountries()
            when(countriesResult.code()){
                in 200..299 -> {
                    try {
                        val body = countriesResult.body()
                        if (body != null) {
                            Result.Success(body)
                        } else {
                            Result.Error(NetworkError.SERIALIZATION)
                        }
                    } catch (e: Exception) {
                        Result.Error(NetworkError.SERIALIZATION)
                    }
                }
                408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)

                429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
                in 500 .. 599 -> Result.Error(NetworkError.SERVER_ERROR)

                else -> Result.Error(NetworkError.UNKNOWN_ERROR)
            }
        }catch (e: IOException) {
            Result.Error(NetworkError.NO_INTERNET_CONNECTION)
        } catch (e: SocketTimeoutException) {
            Result.Error(NetworkError.REQUEST_TIMEOUT)
        }
        catch (e:Exception){
            Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }

    suspend fun searchMeal(query:String) = apiService.searchMeal(query).meals

}