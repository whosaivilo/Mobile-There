package com.example.there_help

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.there_help.data.AppDatabase
import com.example.there_help.data.entity.PengaduanEntity
import com.example.there_help.databinding.ActivityFormPengaduanBinding
import com.example.there_help.utils.NotificationHelper
import com.example.there_help.utils.PermissionHelper
import com.example.there_help.utils.ReminderHelper
import kotlinx.coroutines.launch
import java.util.Calendar

class FormPengaduanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormPengaduanBinding
    private lateinit var db: AppDatabase

    // 1. Setup Launcher buat minta izin notifikasi
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
        binding = ActivityFormPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Inisialisasi AppDatabase
        db = AppDatabase.getInstance(this)

        // 3. Minta izin notifikasi pas halaman dibuka
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            }
        }

        // 4. Aksi Tombol Simpan
        binding.btnSimpanPengaduan.setOnClickListener {
            val inputNama = binding.etNama.text.toString()
            val inputKategori = binding.tvKategori.text.toString()
            val inputIsi = binding.etIsiPengaduan.text.toString()

            // Ambil input menit (pastikan ID etMenit sesuai dengan yang ada di XML kamu)
            val menitReminder = binding.etMenit.text.toString().toIntOrNull() ?: 5

            // Validasi input agar tidak ada yang kosong
            if (inputNama.isNotBlank() && inputKategori.isNotBlank() && inputIsi.isNotBlank()) {

                // Gunakan Coroutine untuk operasi database
                lifecycleScope.launch {
                    val dataPengaduanBaru = PengaduanEntity(
                        nama = inputNama,
                        kategori = inputKategori,
                        isiPengaduan = inputIsi,
                        createdAt = System.currentTimeMillis()
                    )

                    // A. Simpan ke Database Room
                    db.pengaduanDao().insert(dataPengaduanBaru)

                    // B. Setup Intent KHUSUS untuk Notifikasi agar lari ke halaman Detail dengan membawa data
                    val detailIntent = Intent(this@FormPengaduanActivity, DetailPengaduanActivity::class.java).apply {
                        putExtra("EXTRA_NAMA", inputNama)
                        putExtra("EXTRA_KATEGORI", inputKategori)
                        putExtra("EXTRA_ISI", inputIsi)
                    }

                    // C. Tembak Notifikasi Instan "Laporan Berhasil"
                    NotificationHelper.showNotification(
                        context = this@FormPengaduanActivity,
                        title = "Pengaduan Berhasil Dikirim!",
                        message = "Terima kasih $inputNama, klik untuk melihat tiket laporanmu.",
                        intent = detailIntent // Gunakan intent yang sudah berisi data
                    )

                    // D. Pasang Alarm Reminder buat cek status nanti
                    val calendar = Calendar.getInstance().apply {
                        add(Calendar.MINUTE, menitReminder)
                    }

                    ReminderHelper.setReminder(
                        context = this@FormPengaduanActivity,
                        hour = calendar.get(Calendar.HOUR_OF_DAY),
                        minute = calendar.get(Calendar.MINUTE),
                        title = "Cek Status Pengaduan",
                        message = "Sudah $menitReminder menit. Yuk cek apakah keluhan '$inputIsi' sudah diproses!",
                        targetActivity = DetailPengaduanActivity::class.java,
                        nama = inputNama,
                        kategori = inputKategori,
                        isiLaporan = inputIsi
                    )

                    Toast.makeText(this@FormPengaduanActivity, "Laporan terkirim! Reminder diatur untuk $menitReminder menit.", Toast.LENGTH_LONG).show()

                    // E. Navigasi Otomatis saat tombol diklik lari ke Ringkasan
                    val ringkasanIntent = Intent(this@FormPengaduanActivity,
                        DetailPengaduanActivity::class.java)
                    startActivity(ringkasanIntent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Isi semua kolom dulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        // Setup isi Dropdown Kategori (Biar nggak ilang pas reload)
        val daftarKategori = listOf("Infrastruktur", "Layanan Masyarakat", "Keamanan", "Lainnya")
        val adapterKategori = ArrayAdapter(this, R.layout.item_dropdown_kategori, daftarKategori)

        binding.tvKategori.setAdapter(adapterKategori)
    }
}