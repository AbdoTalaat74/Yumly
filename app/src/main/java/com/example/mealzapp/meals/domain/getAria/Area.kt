package com.example.mealzapp.meals.domain.getAria

val areasToSymbolMap = mapOf(
    "American" to "us",
    "British" to "gb",
    "Canadian" to "ca",
    "Chinese" to "cn",
    "Croatian" to "hr",
    "Dutch" to "nl",
    "Egyptian" to "eg",
    "Filipino" to "ph",
    "French" to "fr",
    "Greek" to "gr",
    "Indian" to "in",
    "Irish" to "ie",
    "Italian" to "it",
    "Jamaican" to "jm",
    "Japanese" to "jp",
    "Kenyan" to "kn",
    "Malaysian" to "my",
    "Mexican" to "mx",
    "Moroccan" to "ma",
    "Polish" to "pl",//
    "Portuguese" to "pt",
    "Russian" to "sk",
    "Spanish" to "es",
    "Thai" to "th",
    "Tunisian" to "tn",
    "Turkish" to "tr",
    "Ukrainian" to "UA",
    "Unknown" to "",
    "Vietnamese" to "vn"
)

fun getAreaModel(strArea: String) = "https://www.themealdb.com/images/icons/flags/big/64/${areasToSymbolMap[strArea]}.png"