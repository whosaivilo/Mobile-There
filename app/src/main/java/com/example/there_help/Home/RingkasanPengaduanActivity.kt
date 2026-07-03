package com.example.there_help

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityRingkasanPengaduanBinding // Ganti sesuai package

class RingkasanPengaduanActivity : AppCompatActivity() {

    // 1. Deklarasi variabel binding
    private lateinit var binding: ActivityRingkasanPengaduanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inisialisasi binding dan pasang ke layar
        binding = ActivityRingkasanPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Nangkep data lemparan dari notifikasi/reminder (pakai key "LAPORAN")
        val laporan = intent.getStringExtra("LAPORAN") ?: "Tidak ada data pengaduan"

        // 4. Tampilin ke TextView yang ada di layout XML
        binding.tvDetailLaporan.text = "Keluhan Anda: $laporan\n\nStatus saat ini: Menunggu Konfirmasi Admin"
    }
}