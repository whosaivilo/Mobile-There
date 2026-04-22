package com.example.there_help.pertemuan_3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityCustomBinding
import com.google.android.material.snackbar.Snackbar

class CustomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Log LifeCycle: onCreate [cite: 27]
        Log.e("onCreate", "CustomActivity dibuat pertama kali")

        // 2. Ambil data Intent dari MainActivity [cite: 11, 12]
        val judul = intent.getStringExtra("title")
        val deskripsi = intent.getStringExtra("desc")

        // Tampilkan data ke UI sesuai kiriman intent [cite: 14]
        binding.tvJudulHalaman.text = judul
        binding.tvDescHalaman.text = deskripsi

        // 3. Logika Button & Card
        binding.btnMulaiBelajar.setOnClickListener {
            Toast.makeText(this, "Memulai materi: $judul", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: CustomActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "CustomActivity dihapus dari stack")
    }
}