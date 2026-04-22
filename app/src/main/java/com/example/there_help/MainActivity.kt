package com.example.there_help

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityMainBinding
import com.example.there_help.pertemuan_3.CustomActivity
import com.example.there_help.pertemuan_3.LoginActivity
import com.example.there_help.pertemuan_3.LoginResultActivity
import com.example.there_help.tugasp2.Kalkulator
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
            intent.putExtra("desc", "Halaman ini digunakan untuk menghitung volume dan luas.")
            startActivity(intent)
        }

        // Tombol 2: Navigasi ke Skor Finansial (Result Login)
        binding.btnKeResult.setOnClickListener {
            val intent = Intent(this, LoginResultActivity::class.java)
            intent.putExtra("title", "Status Finansial")
            intent.putExtra("desc", "Cek kesehatan keuangan UMKM Anda di sini.")
            startActivity(intent)
        }

        // Tombol 3: Halaman Custom (Contoh SnackBar)
        binding.btnKeCustom2.setOnClickListener {
            // 1. Buat Intent menuju CustomActivity
            val intent = Intent(this, CustomActivity::class.java)

            intent.putExtra("title", "Edukasi Keuangan")
            intent.putExtra("desc", "Kenali mata uang kita untuk ekonomi yang lebih stabil.")

            startActivity(intent)
        }
        // Tombol 4: Logout dengan Alert Konfirmasi
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Ya") { _, _ ->
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Hapus MainActivity dari stack agar tidak bisa Back
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT)
                        .show()
                }
                .show()

        }
    }
}

