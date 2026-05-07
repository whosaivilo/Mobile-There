package com.example.there_help.pertemuan_3

import android.os.Bundle
import android.util.Log // Import untuk mencatat log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.there_help.databinding.ActivityLoginResultBinding

class LoginResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val judul = intent.getStringExtra("title")
        val deskripsi = intent.getStringExtra("desc")
        binding.tvJudulHalaman.text = judul
        binding.tvDescHalaman.text = deskripsi

        Log.e("Data Intent", "Judul: $judul, Deskripsi: $deskripsi")
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