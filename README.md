# Yumly
## Overview
**Yumly** is a modern Android application designed to help users discover, explore, and prepare delicious meals from around the world. With an intuitive interface built using the latest technologies, Yumly makes cooking fun and accessible for everyone.
---
## Features
- **Random Meal Inspiration**: Display five random meals on the home screen for fresh ideas every time.
- **Category-based Browsing**: Explore recipes organized by meal types and categories.
- **Ingredient-based Filtering**: Find meals that include specific ingredients you have on hand.
- **International Cuisine**: Discover meals by country to explore global flavors and cooking styles.
- **Search Functionality**: Perform precise searches by meal name to quickly find specific recipes.
- **Daily Meal Suggestions**: Receive daily notifications with random meal suggestions to expand your culinary horizons.
- **Comprehensive Recipe Resources**: Each meal includes source links and YouTube video tutorials for easy preparation.
---
## Tech Stack
### 🚠 Architectural Pattern
- **Clean Architecture**: 
  - Modular structure with clear separation of concerns.
  - Divided into layers for improved maintainability and testability.
- **MVVM (Model-View-ViewModel)**: 
  - Enables reactive UI updates based on data changes.
  - Improves code organization and testing capabilities.
### 💻 UI Framework
- **Jetpack Compose**: 
  - For building a responsive, declarative, and modern UI.
### ⚙️ Dependency Injection
- **Dagger Hilt**: 
  - Simplifies dependency management, ensuring modularity and testability.
### 🔄 Asynchronous Programming
- **Coroutines**: 
  - For efficient and seamless handling of background operations.
### 🌐 Networking
- **Retrofit**: 
  - For efficient data fetching from APIs over the network.
### 🖼️ Image Loading
- **Coil**: 
  - For loading and displaying images effectively with minimal boilerplate.
---
## Installation
### Prerequisites
- Android Studio `Arctic Fox` or newer.
- Minimum Android SDK level: `21`.
### Steps to Run the App
1. Clone this repository.
2. Open the project in Android Studio.
3. Sync the project to download dependencies.
4. Run the app on an emulator or a physical device.
### Download
You can download the app directly from:
[Yumly App Download](https://drive.google.com/drive/u/0/folders/150d4Y_iXrcSV0MvVGXRPs_ZxLyAoc03e)
---
## Folder Structure
```
├── data
│   ├── repository   # Data sources implementation
│   ├── local        # Room database and DAO classes
├── domain
│   ├── model        # Business models
│   ├── repository   # Repository interfaces
│   ├── usecase      # Application business logic
├── presentation
│   ├── ui           # Composables and screens
│   ├── viewmodel    # State and logic management
├── di               # Dependency injection modules
└── utils            # Helper classes and functions
```
## Screenshots
<img src="https://github.com/user-attachments/assets/f8045045-78c0-4819-95ae-7fc564af21ad" alt="description" width="200"/>

<img src="https://github.com/user-attachments/assets/9ff2e043-ca7f-4d5c-9f84-131e8aae7ade" alt="description" width="200"/>

<img src="https://github.com/user-attachments/assets/91e4c78e-4bc6-4af8-88ce-8cf961f12ac3" alt="description" width="200"/>

<img src="https://github.com/user-attachments/assets/24982395-0664-4737-95ad-5bdc913f83dc" alt="description" width="200"/>


---
## Acknowledgements
- **The Meal DB**: For providing the food recipe API.
- **Google**: For Jetpack Compose and Android architecture guidelines.
---
## Contact
For any inquiries, please reach out to:
- **Abdelrahman Talaat**
- Email: abdelrahmant.dev@gmail.com
- GitHub: https://github.com/AbdoTalaat74
