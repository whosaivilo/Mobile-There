package com.example.there_help.utils // Sesuaikan package lu

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar

class ReminderHelper {

    companion object {
        fun setReminder(
            context: Context,
            hour: Int,
            minute: Int,
            title: String,
            message: String,
            targetActivity: Class<*>,
            nama: String,
            kategori: String,
            isiLaporan: String
        ) {

            val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                if (before(Calendar.getInstance())) {
                    add(Calendar.DAY_OF_MONTH, 1)
                }
            }

            val intent = Intent(context, ReminderReceiver::class.java).apply {
                putExtra("title", title)
                putExtra("message", message)
                putExtra("target_activity", targetActivity.name)
                putExtra("EXTRA_NAMA", nama)
                putExtra("EXTRA_KATEGORI", kategori)
                putExtra("EXTRA_ISI", isiLaporan)
            }
            val requestCode = System.currentTimeMillis().toInt()
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val alarmManager =
                context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }
}