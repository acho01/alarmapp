package com.asharashenidze.alarmapp

import android.app.Notification
import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat


class AlarmReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "message received")
        context?.let {
            var notificationManager = NotificationManagerCompat.from(context)

            val cancelIntent = Intent(CANCEL_ACTION_NAME)
            val snoozeIntent = Intent(SNOOZE_ACTION_NAME)
            val notificationCancelIntent =
                    PendingIntent.getBroadcast(context, 0, cancelIntent, 0)
            val notificationSnoozeIntent =
                    PendingIntent.getBroadcast(context, 0, snoozeIntent, 0)

            val time: String = intent?.getStringExtra("time").orEmpty()
            val id: String = intent?.getStringExtra("id").orEmpty()

            var notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createChannel(notificationManager)
                Notification.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.alarm_clock)
                    .setContentTitle("Your Alarm")
                    .setContentText("Alarm set on ${time}")
                        .addAction(R.mipmap.ic_launcher, "Cancel", notificationCancelIntent)
                        .addAction(R.mipmap.ic_launcher, "Snooze", notificationSnoozeIntent)
                        .build()
            } else {
                Notification.Builder(context)
                    .setSmallIcon(R.drawable.alarm_clock)
                    .setContentTitle("Message")
                    .setContentText("I am from notifications demo app")
                    .build()
            }

            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(notificationManager: NotificationManagerCompat) {
        var notificationChannel = NotificationChannel(CHANNEL_ID, "channel_name", IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)
    }
    companion object{
        const val TAG = "ALARM_REVIECER"
        const val ALARM_ACTION_NAME = "com.asharashenidze.alarmapp.ALARM_ACTION"
        const val CANCEL_ACTION_NAME = "com.asharashenidze.alarmapp.CANCEL_ACTION"
        const val SNOOZE_ACTION_NAME = "com.asharashenidze.alarmapp.SNOOZE_ACTION"

        const val NOTIFICATION_ID = 22
        const val CHANNEL_ID = "com.asharashenidze.alarmapp.CHANNEL"

    }
}