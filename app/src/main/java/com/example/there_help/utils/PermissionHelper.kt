package com.example.there_help.utils // Pastiin package-nya tetep ini ya cuq

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

class PermissionHelper {

    companion object {

        // 1. Fungsi Kunci Inggris: Bisa buat cek izin APAPUN (Kamera, Notif, Lokasi, dll)
        fun hasPermission(context: Context, permission: String): Boolean {
            return ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }

        // 2. Fungsi Kunci Inggris: Buat memunculkan pop-up minta izin ke warga
        fun requestPermission(
            launcher: ActivityResultLauncher<String>,
            permission: String
        ) {
            launcher.launch(permission)
        }

        // 3. Khusus ngecek syarat notifikasi (Karena Android 13/Tiramisu ke atas aturannya beda)
        fun isNotificationPermissionRequired(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        }

        fun hasCameraPermission(context: Context): Boolean {
            return ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}