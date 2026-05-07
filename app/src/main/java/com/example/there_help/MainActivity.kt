package com.example.there_help

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityMainBinding
import com.example.there_help.pertemuan_4.CustomActivity
import com.example.there_help.AuthActivity
import com.example.there_help.pertemuan_3.LoginResultActivity
import com.example.there_help.tugasp2.Kalkulator

import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol 1: Navigasi ke Kalkulator dengan data
        binding.btnKeKalkulator.setOnClickListener {
            val intent = Intent(this, Kalkulator::class.java)
            intent.putExtra("title", "Rumus Bangun Ruang")
            intent.putExtra("desc", "Halaman ini digunakan untuk menghitung volume dan luas")
            startActivity(intent)
        }

        // Tombol 2: Navigasi ke Skor Finansial (Result Login)
        binding.btnKeResult.setOnClickListener {
            val intent = Intent(this, LoginResultActivity::class.java)
            intent.putExtra("title", "Status Finansial")
            intent.putExtra("desc", "Cek kesehatan keuangan UMKM Anda di sini")
            startActivity(intent)
        }

        // Tombol 3: Halaman Custom
        binding.btnKeCustom2.setOnClickListener {
            val intent = Intent(this, CustomActivity::class.java)
            intent.putExtra("title", "Edukasi Keuangan")
            intent.putExtra("desc", "Kenali mata uang kita untuk ekonomi yang lebih stabil")

            startActivity(intent)

            }
        // Tombol 4: Logout dengan Alert Konfirmasi
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Keluar dari aplikasi Bina Desa?")
                .setPositiveButton("Ya") { _, _ ->
                    val sharedPref = getSharedPreferences("user_pref",MODE_PRIVATE)
                    sharedPref.edit().clear().apply() // Hapus semua session

                    startActivity(Intent(this, AuthActivity::class.java))
                    finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }
}

