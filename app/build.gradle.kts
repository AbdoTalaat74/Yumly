plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt") // Apply KAPT
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.mealzapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mealzapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.retrofit)
    implementation(libs.gsonConverter)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.retrofit)
    implementation(libs.androidx.navigation.compose) // Compose navigation
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.hilt.android)
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.coil.compose)

    implementation("com.google.accompanist:accompanist-pager:0.28.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.28.0")
    implementation("me.saket.telephoto:zoomable:0.13.0")
    implementation("androidx.compose.material3:material3-window-size-class:1.3.1")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.4.0-alpha03")
    implementation("androidx.core:core-splashscreen:1.0.0")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")





}

kapt {
    correctErrorTypes = true
}