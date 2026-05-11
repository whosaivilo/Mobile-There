package com.example.there_help

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityMainBinding
// Import halaman lain jika beda folder (package)
import com.example.there_help.Home.tugasp2.Kalkulator
import com.example.there_help.Home.pertemuan_3.LoginResultActivity
import com.example.there_help.Home.pertemuan_4.CustomActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- 1. KONFIGURASI TOOLBAR ---
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "KASPRO - Home"
        }

        // --- 2. LOGIKA NAVIGASI TOMBOL (TUGAS & IMPROVISASI) ---

        // Tombol ke WebView (Tugas Nomor 2)
        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        // Tombol ke Kalkulator (Tugas P2)
        binding.btnKeKalkulator.setOnClickListener {
            val intent = Intent(this, Kalkulator::class.java)
            intent.putExtra("title", "Kalkulator")
            intent.putExtra("desc", "Hitung Luas Persegi & Volume Balok")
            startActivity(intent)
        }

        // Tombol ke Hasil Skor (Pertemuan 3)
        binding.btnKeResult.setOnClickListener {
            val intent = Intent(this, LoginResultActivity::class.java)
            intent.putExtra("title", "Hasil Skor")
            intent.putExtra("desc", "Data statistik login pengguna")
            startActivity(intent)
        }

        // Tombol ke Custom Activity (Pertemuan 4)
        binding.btnKeCustom2.setOnClickListener {
            val intent = Intent(this, CustomActivity::class.java)
            intent.putExtra("title", "Custom View")
            intent.putExtra("desc", "Materi belajar kustomisasi UI")
            startActivity(intent)
        }

        // Tombol Logout Manual (Sudah Diperbaiki)
        binding.btnLogout.setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Ya, Keluar") { _, _ ->
                    // 1. Hapus memori sesi login!
                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    sharedPref.edit().clear().apply()

                    // 2. Lempar balik ke halaman Login
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)

                    // 3. Tutup halaman Dashboard ini
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss() // Tutup dialog, nggak jadi logout
                }
                .show()
        }
    }

    // --- 3. PASANG MENU (Search & Settings) ---
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // --- 4. HANDLE KLIK PADA MENU ---
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "Membuka Pencarian...", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sub_profile -> {
                Toast.makeText(this, "Membuka Edit Profil...", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sub_theme -> {
                Toast.makeText(this, "Pilih Tema Aplikasi...", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sub_logout -> {
                Toast.makeText(this, "Sesi Berakhir", Toast.LENGTH_SHORT).show()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}