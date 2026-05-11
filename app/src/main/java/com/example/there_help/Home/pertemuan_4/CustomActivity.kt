package com.example.there_help.Home.pertemuan_4

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.R // Pastikan import ini ada untuk memanggil ic_arrow
import com.example.there_help.databinding.ActivityCustomBinding

class CustomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        val judul = intent.getStringExtra("title")
        val deskripsi = intent.getStringExtra("desc")

        supportActionBar?.apply {
            // Jika judul dari intent ada, pakai itu. Jika tidak, pakai "Materi"
            title = judul ?: "Detail Materi"

            setDisplayHomeAsUpEnabled(true) // Munculkan tombol back
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow) // Ikon panah kustommu
        }
        // -----------------------------------------

        binding.tvJudulHalaman.text = judul
        binding.tvDescHalaman.text = deskripsi

        binding.btnMulaiBelajar.setOnClickListener {
            Toast.makeText(this, "Memulai materi: $judul", Toast.LENGTH_SHORT).show()
        }
    }

    // WAJIB: Logika agar tombol back (panah) beneran bisa diklik
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "CustomActivity dihapus dari stack")
    }
}