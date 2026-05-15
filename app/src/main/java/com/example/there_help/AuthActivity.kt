package com.example.there_help

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityAuthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder // <-- IMPORT INI WAJIB BUAT SOAL 3

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi View Binding
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Logika Klik Tombol Login
        binding.btnLogin.setOnClickListener {
            val inputUsername = binding.email.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString()

            // --- AMBIL DATA DARI SHARED PREFERENCES (Kunci 2) ---
            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val savedUsername = sharedPref.getString("REG_USERNAME", "")
            val savedPassword = sharedPref.getString("REG_PASSWORD", "")

            // Cek apakah (Rule 1: input == password) ATAU (Rule 2: input cocok dengan data regis)
            if ((inputUsername == password && inputUsername.isNotEmpty()) ||
                (inputUsername == savedUsername && password == savedPassword && inputUsername.isNotEmpty())) {

                // Simpan status login ke Shared Preferences
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.apply()
                Toast.makeText(this, "Login Berhasil, Selamat Datang!", Toast.LENGTH_SHORT).show()

                // Pindah ke Dashboard
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                // PESAN ERROR MENGGUNAKAN MATERIAL ALERT DIALOG (SYARAT SOAL 3)
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau password salah! Pastikan data sesuai dengan registrasi.")
                    .setPositiveButton("Coba Lagi") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        // Tombol menuju halaman Input Email (Register with Gmail - SOAL B1)
        binding.btnRegisterGmail.setOnClickListener {
            val intent = Intent(this, InputEmailActivity::class.java)
            startActivity(intent)
        }
    }
}