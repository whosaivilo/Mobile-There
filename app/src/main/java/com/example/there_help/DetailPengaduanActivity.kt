package com.example.there_help

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityDetailPengaduanBinding

class DetailPengaduanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPengaduanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar & Tombol Back
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarDetail.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 2. Ambil data yang dikirim dari halaman sebelumnya (atau dari Notifikasi/Reminder)
        // Jika tidak ada data yang dikirim, kita tampilkan teks "Data tidak ditemukan"
        val nama = intent.getStringExtra("EXTRA_NAMA") ?: "Pengguna Anonim"
        val kategori = intent.getStringExtra("EXTRA_KATEGORI") ?: "Belum dikategorikan"

        // Cek "EXTRA_ISI" dulu, kalau kosong cek "LAPORAN" (dari kodingan form kamu sebelumnya)
        val isiLaporan = intent.getStringExtra("EXTRA_ISI") ?: intent.getStringExtra("LAPORAN")
        ?: "Tidak ada detail laporan."

        // 3. Pasang data ke antarmuka (UI)
        binding.tvDetailNama.text = nama
        binding.tvDetailKategori.text = kategori
        binding.tvDetailIsi.text = isiLaporan

        // 4. Set Status Default (Bisa juga dibikin dinamis nanti kalau dari Database)
        binding.tvDetailStatus.text = "Menunggu Verifikasi"
    }
}