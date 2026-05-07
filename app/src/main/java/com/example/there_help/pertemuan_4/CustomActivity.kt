package com.example.there_help.pertemuan_4

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityCustomBinding

class CustomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val judul = intent.getStringExtra("title")
        val deskripsi = intent.getStringExtra("desc")

        binding.tvJudulHalaman.text = judul
        binding.tvDescHalaman.text = deskripsi
        binding.btnMulaiBelajar.setOnClickListener {
            Toast.makeText(this, "Memulai materi: $judul", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "CustomActivity dihapus dari stack")
    }
}