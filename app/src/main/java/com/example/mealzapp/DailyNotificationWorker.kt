package com.example.mealzapp

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mealzapp.meals.presentation.main.MainViewModel

class DailyNotificationWorker( context: Context, workerParams: WorkerParameters)
    :Worker(context, workerParams) {
    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }

    private fun showNotification() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        MainViewModel.SharedData.data.value?.let {
            val randomMeal = MainViewModel.SharedData.data.value?.first()
            val notification = NotificationCompat.Builder(
                applicationContext,
                "daily_meal_channel"
            )
                .setContentText(randomMeal?.strMeal)
                .setContentTitle("Your Daily Meal")
                .setColor(0xFFFF5722.toInt())
                .setSmallIcon(R.drawable.yumly_notification_icon)
                .build()
            notificationManager.notify(1, notification)
        }


    }

}