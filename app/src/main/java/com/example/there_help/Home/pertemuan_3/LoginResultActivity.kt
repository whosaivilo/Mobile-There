package com.example.there_help.Home.pertemuan_3

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.there_help.R // Jangan lupa import R
import com.example.there_help.databinding.ActivityLoginResultBinding

class LoginResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- KONFIGURASI TOOLBAR (TUGAS NO 1) ---
        setSupportActionBar(binding.toolbar)

        val judul = intent.getStringExtra("title")
        val deskripsi = intent.getStringExtra("desc")

        supportActionBar?.apply {
            title = judul ?: "Hasil Login" // Pakai judul dari intent atau default
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow) // Pakai panah buatanmu
        }
        // -----------------------------------------

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tvJudulHalaman.text = judul
        binding.tvDescHalaman.text = deskripsi

        Log.e("Data Intent", "Judul: $judul, Deskripsi: $deskripsi")
    }

    // Fungsi sakti buat aktifin tombol back
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: LoginResultActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "LoginResultActivity dihapus dari stack")
    }
}