package com.example.there_help.Profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.Home.HelpActivity
import com.example.there_help.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Konfigurasi Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Setting"
            setDisplayHomeAsUpEnabled(true) // Menampilkan tombol back (panah kiri)
        }

        // Fungsi agar tombol back di toolbar bekerja
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.tvPusatBantuan.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }
    }
}