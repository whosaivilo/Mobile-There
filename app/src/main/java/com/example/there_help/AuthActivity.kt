package com.example.there_help

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aktifkan tampilan layar penuh (Edge to Edge)
        enableEdgeToEdge()

        // Inisialisasi View Binding
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

                // 2. Beri feedback sukses
                Toast.makeText(this, "Login Berhasil, Selamat Datang!", Toast.LENGTH_SHORT).show()

                // 3. Pindah ke MainActivity
                // Karena MainActivity satu package (com.example.there_help), tidak butuh import tambahan
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // 4. Tutup AuthActivity agar user tidak bisa 'back' ke halaman login lagi
                finish()

            } else {
                // Tampilkan pesan error jika login gagal
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