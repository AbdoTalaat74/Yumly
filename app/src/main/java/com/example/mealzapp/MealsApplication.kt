package com.example.mealzapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MealsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val channelId = "daily_meal_channel"
        val channelName = "Daily Meal Notification"
        val channelDescription = "Notification for daily meal suggestions"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = channelDescription
        }
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }


}