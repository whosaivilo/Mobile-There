package com.example.there_help.tugasp2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.R // Pastikan import R ini ada
import com.example.there_help.databinding.ActivityKalkulatorBinding

class Kalkulator : AppCompatActivity() {
    private lateinit var binding: ActivityKalkulatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityKalkulatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- BAGIAN TOOLBAR (TUGAS NO 1) ---
        setSupportActionBar(binding.toolbar) // Pasang Toolbar

        // Mengambil data Intent untuk Judul & Deskripsi
        val judul = intent.getStringExtra("title")
        val deskripsi = intent.getStringExtra("desc")

        supportActionBar?.apply {
            title = judul ?: "Kalkulator" // Judul Toolbar pakai data dari Intent
            setDisplayHomeAsUpEnabled(true) // Aktifkan tombol back
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow) // Pakai ikon kustommu
        }
        // ------------------------------------

        Log.e("onCreate", "Kalkulator dibuat pertama kali")

        // Tampilkan data ke TextView
        binding.tvJudul.text = judul
        binding.tvDeskripsi.text = deskripsi

        Log.e("Data Intent", "Judul: $judul, Deskripsi: $deskripsi")

        // Logika Hitung Luas Persegi
        binding.btnHitungPersegi.setOnClickListener {
            val sisiStr = binding.inputSisi.text.toString()
            if (sisiStr.isNotEmpty()) {
                val sisi = sisiStr.toDouble()
                val luas = sisi * sisi
                binding.tvHasilPersegi.text = "Hasil Luas: $luas"
                Toast.makeText(this, "Luas Persegi berhasil dihitung!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Sisi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }

        // Logika Hitung Volume Balok
        binding.btnHitungBalok.setOnClickListener {
            val pStr = binding.inputPanjang.text.toString()
            val lStr = binding.inputLebar.text.toString()
            val tStr = binding.inputTinggi.text.toString()

            if (pStr.isNotEmpty() && lStr.isNotEmpty() && tStr.isNotEmpty()) {
                val volume = pStr.toDouble() * lStr.toDouble() * tStr.toDouble()
                binding.tvHasilBalok.text = "Hasil Volume: $volume"
                Log.i("LogKalkulator", "Hitung Balok berhasil. Volume: $volume")
                Toast.makeText(this, "Volume Balok berhasil dihitung!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Semua input balok harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: Kalkulator terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "Kalkulator dihapus dari stack")
    }
}