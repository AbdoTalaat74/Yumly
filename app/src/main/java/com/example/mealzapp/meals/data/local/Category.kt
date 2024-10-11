package com.example.mealzapp.meals.data.local

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)






val categories = listOf(
    Category(
        idCategory = "1",
        strCategory = "Beef",
        strCategoryThumb = "https://www.themealdb.com/images/category/beef.png",
        strCategoryDescription = "Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times. Beef is a source of high-quality protein and essential nutrients."
    ),
    Category(
        idCategory = "2",
        strCategory = "Chicken",
        strCategoryThumb = "https://www.themealdb.com/images/category/chicken.png",
        strCategoryDescription = "Chicken is a type of domesticated fowl, a subspecies of the red junglefowl. It is one of the most common and widespread domestic animals, with a total population of more than 19 billion as of 2011."
    ),
    Category(
        idCategory = "3",
        strCategory = "Dessert",
        strCategoryThumb = "https://www.themealdb.com/images/category/dessert.png",
        strCategoryDescription = "Dessert is a course that concludes a meal. The course usually consists of sweet foods, such as confections dishes or fruit, and possibly a beverage such as dessert wine or liqueur."
    ),
    Category(
        idCategory = "4",
        strCategory = "Lamb",
        strCategoryThumb = "https://www.themealdb.com/images/category/lamb.png",
        strCategoryDescription = "Lamb, hogget, and mutton are the meat of domestic sheep at different ages. A sheep in its first year is called a lamb, and its meat is also called lamb."
    ),
    Category(
        idCategory = "5",
        strCategory = "Miscellaneous",
        strCategoryThumb = "https://www.themealdb.com/images/category/miscellaneous.png",
        strCategoryDescription = "General foods that don't fit into another category."
    ),
    Category(
        idCategory = "6",
        strCategory = "Pasta",
        strCategoryThumb = "https://www.themealdb.com/images/category/pasta.png",
        strCategoryDescription = "Pasta is a staple food of traditional Italian cuisine, with the first reference dating to 1154 in Sicily. It is typically a noodle made from durum wheat flour mixed with water or eggs."
    ),
    Category(
        idCategory = "7",
        strCategory = "Pork",
        strCategoryThumb = "https://www.themealdb.com/images/category/pork.png",
        strCategoryDescription = "Pork is the culinary name for meat from a domestic pig. It is the most commonly consumed meat worldwide, with evidence of pig husbandry dating back to 5000 BC."
    ),
    Category(
        idCategory = "8",
        strCategory = "Seafood",
        strCategoryThumb = "https://www.themealdb.com/images/category/seafood.png",
        strCategoryDescription = "Seafood is any form of sea life regarded as food by humans, prominently including fish and shellfish."
    ),
    Category(
        idCategory = "9",
        strCategory = "Side",
        strCategoryThumb = "https://www.themealdb.com/images/category/side.png",
        strCategoryDescription = "A side dish, sometimes referred to as a side order, is a food item that accompanies the entrée or main course at a meal."
    ),
    Category(
        idCategory = "10",
        strCategory = "Starter",
        strCategoryThumb = "https://www.themealdb.com/images/category/starter.png",
        strCategoryDescription = "An entrée in French table service is a dish served before the main course of a meal."
    ),
    Category(
        idCategory = "11",
        strCategory = "Vegan",
        strCategoryThumb = "https://www.themealdb.com/images/category/vegan.png",
        strCategoryDescription = "Veganism is both the practice of abstaining from the use of animal products, particularly in diet, and an associated philosophy that rejects the commodity status of animals."
    ),
    Category(
        idCategory = "12",
        strCategory = "Vegetarian",
        strCategoryThumb = "https://www.themealdb.com/images/category/vegetarian.png",
        strCategoryDescription = "Vegetarianism is the practice of abstaining from the consumption of meat, and may include abstention from by-products of animal slaughter."
    ),
    Category(
        idCategory = "13",
        strCategory = "Breakfast",
        strCategoryThumb = "https://www.themealdb.com/images/category/breakfast.png",
        strCategoryDescription = "Breakfast is the first meal of a day, typically consisting of preparations and ingredients that vary widely from place to place."
    ),
    Category(
        idCategory = "14",
        strCategory = "Goat",
        strCategoryThumb = "https://www.themealdb.com/images/category/goat.png",
        strCategoryDescription = "The domestic goat is a subspecies of wild goat, and has been used for milk, meat, fur, and skins across much of the world."
    )
)