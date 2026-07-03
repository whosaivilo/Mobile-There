package com.example.there_help

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityFormPengaduanBinding
import com.example.there_help.utils.NotificationHelper
import com.example.there_help.utils.PermissionHelper
import com.example.there_help.utils.ReminderHelper
import java.util.Calendar
import kotlin.jvm.java

class FormPengaduanActivity : AppCompatActivity() {

    // 1. Deklarasi variabel binding secara utuh
    private lateinit var binding: ActivityFormPengaduanBinding

    // 2. Setup Launcher buat minta izin notifikasi dari Helper
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Sip, notifikasi aktif!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Yah, Izin Notifikasi Ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 3. Inisialisasi binding dan pasang ke layar (setContentView)
        binding = ActivityFormPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 4. Minta izin notifikasi pas halaman dibuka (Sesuai Modul)
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            }
        }

        // 5. Aksi tombol Kirim Pengaduan
        binding.btnKirim.setOnClickListener {
            val isiLaporan = binding.edtLaporan.text.toString()
            // Kalau warga gak ngisi menit, default-nya kita set 5 menit
            val menitReminder = binding.edtMenit.text.toString().toIntOrNull() ?: 5

            // Setup Intent buat dilempar ke halaman Ringkasan
            val targetIntent = Intent(this, RingkasanPengaduanActivity::class.java).apply {
                putExtra("LAPORAN", isiLaporan)
            }

            // A. Tembak Notifikasi Instan "Laporan Berhasil"
            NotificationHelper.showNotification(
                context = this,
                title = "Pengaduan Berhasil Dikirim!",
                message = "Terima kasih, laporanmu sedang kami rekap. Klik untuk melihat ringkasan.",
                intent = targetIntent
            )

            // B. Pasang Alarm Reminder buat cek status nanti
            val calendar = Calendar.getInstance().apply {
                add(Calendar.MINUTE, menitReminder)
            }

            ReminderHelper.setReminder(
                context = this,
                hour = calendar.get(Calendar.HOUR_OF_DAY),
                minute = calendar.get(Calendar.MINUTE),
                title = "Cek Status Pengaduan",
                message = "Sudah $menitReminder menit berlalu. Yuk cek apakah keluhan '$isiLaporan' sudah diproses admin!",
                targetActivity = RingkasanPengaduanActivity::class.java
            )

            // Kasih tau warga kalau berhasil
            Toast.makeText(this, "Laporan terkirim! Reminder diatur untuk $menitReminder menit.", Toast.LENGTH_LONG).show()

            // Kosongin form setelah kirim biar rapi
            binding.edtLaporan.text?.clear()
            binding.edtMenit.text?.clear()
        }
    }
}