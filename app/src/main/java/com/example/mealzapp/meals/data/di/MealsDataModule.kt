package com.example.mealzapp.meals.data.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.mealzapp.meals.data.remote.MealsApiService
import com.example.mealzapp.meals.domain.util.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MealsDataModule {

    @Provides
    @Singleton
    fun provideConnectivityManager(app: Application): ConnectivityManager {
        return app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    // Provides the NetworkHelper
    @Provides
    @Singleton
    fun provideNetworkHelper(connectivityManager: ConnectivityManager): NetworkHelper {
        return NetworkHelper(connectivityManager)
    }

    @Provides
    fun provideApiService(retrofit: Retrofit):MealsApiService{
        return retrofit.create(MealsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .build()
    }
}