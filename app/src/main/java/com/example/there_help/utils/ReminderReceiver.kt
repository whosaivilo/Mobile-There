package com.example.there_help.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.there_help.MainActivity

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Nangkep data yang dikirim dari ReminderHelper
        val title = intent.getStringExtra("title") ?: "Pengingat"
        val message = intent.getStringExtra("message") ?: "Waktunya cek status laporan!"
        val targetClassName = intent.getStringExtra("target_activity")

        // 1. TANGKAP DATANYA (Ini udah bener)
        val nama = intent.getStringExtra("EXTRA_NAMA")
        val kategori = intent.getStringExtra("EXTRA_KATEGORI")
        val isiLaporan = intent.getStringExtra("EXTRA_ISI")

        // Buka halaman tujuan (Misal: DetailPengaduanActivity)
        val targetIntent = if (!targetClassName.isNullOrEmpty()) {
            val clazz = Class.forName(targetClassName)
            Intent(context, clazz).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                // 2. NAH, INI YANG KURANG! Masukin lagi datanya ke targetIntent
                putExtra("EXTRA_NAMA", nama)
                putExtra("EXTRA_KATEGORI", kategori)
                putExtra("EXTRA_ISI", isiLaporan)
            }
        } else {
            Intent(context, MainActivity::class.java)
        }

        // Manggil NotificationHelper buat nampilin pop-up di HP warga
        NotificationHelper.showNotification(
            context = context,
            title = title,
            message = message,
            intent = targetIntent
        )
    }
}