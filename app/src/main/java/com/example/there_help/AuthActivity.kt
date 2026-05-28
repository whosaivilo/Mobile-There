package com.example.there_help

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityAuthBinding
import kotlin.jvm.java

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Inisialisasi View Binding
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterGmail.setOnClickListener {
            // Ganti 'RegisterGmailActivity' dengan nama file KOTLIN dari layout baru yang lu buat tadi ya!
            val intent = Intent(this, InputEmailActivity::class.java)
            startActivity(intent)
        }

        // Logika Klik Tombol Login
        binding.btnLogin.setOnClickListener {
            val userEmail = binding.email.text.toString()
            val password = binding.editTextTextPassword.text.toString()

            // Cek apakah email sama dengan password dan tidak kosong
            if (userEmail == password && userEmail.isNotEmpty()) {

                // 1. Simpan status login ke Shared Preferences
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.apply()
                Toast.makeText(this, "Login Berhasil, Selamat Datang!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()

            } else {

                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Email atau password salah. Pastikan email dan password sama!")
                    .setPositiveButton("Coba Lagi", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        }
    }
}