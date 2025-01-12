package com.example.mealzapp.meals.core
import com.example.mealzapp.meals.domain.util.Error

enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET_CONNECTION,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN_ERROR,
    NO_RESULTS_FOUND
}
