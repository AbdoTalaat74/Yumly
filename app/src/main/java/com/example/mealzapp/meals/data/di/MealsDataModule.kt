package com.example.mealzapp.meals.data.di

import com.example.mealzapp.meals.data.remote.MealsApiService
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