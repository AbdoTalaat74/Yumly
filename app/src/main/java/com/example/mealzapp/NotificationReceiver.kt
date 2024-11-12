package com.example.mealzapp

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.example.mealzapp.meals.presentation.main.MainViewModel

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("NotificationReceiverCheck", "NotificationReceiver is working")
        if (context != null) {
            showNotification(context)
        }
    }

    private fun showNotification(context: Context) {
        MainViewModel.SharedData.notificationRandomMeal.value?.let { mealsList ->
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val mealId = mealsList.first().idMeal

            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = "myapp://meal/$mealId".toUri()
            }

            val pendingIntent = TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(intent)
                getPendingIntent(
                    1234,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }
            val notification = NotificationCompat.Builder(
                context,
                "daily_meal_channel"
            )
                .setContentText(mealsList.first().strMeal)
                .setContentTitle("Your Daily Meal")
                .setColor(0xFFFF5722.toInt())
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.yumly_notification_icon)
                .build()

            notificationManager.notify(1, notification)
        }
    }

}