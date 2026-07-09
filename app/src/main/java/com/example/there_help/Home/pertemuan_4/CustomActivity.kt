package com.example.there_help.Home.pertemuan_4

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.R
import com.example.there_help.databinding.ActivityCustomBinding

class CustomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomBinding

    override fun onStart() {
        super.onStart()
        Log.e("Lifecycle", "CustomActivity: onStart dipanggil")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        // Nangkap data dari HomeFragment
        val judul = intent.getStringExtra("title")
        val deskripsi = intent.getStringExtra("desc")

        supportActionBar?.apply {
            title = "Detail Materi"

            setDisplayHomeAsUpEnabled(true) // Munculkan tombol back
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow) // Ikon panah kustommu
        }
        // -----------------------------------------

        // Data dari putExtra tetep masuk ke layar halaman lu
        binding.tvJudulHalaman.text = judul ?: "Detail Materi"
        binding.tvDescHalaman.text = deskripsi ?: "Deskripsi tidak tersedia"

        binding.btnMulaiBelajar.setOnClickListener {
            Toast.makeText(this, "Memulai materi: ${judul ?: "Materi"}", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "CustomActivity dihapus dari stack")
    }
}