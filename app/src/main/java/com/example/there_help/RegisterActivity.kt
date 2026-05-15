package com.example.there_help

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val emailGmail = intent.getStringExtra("EMAIL_GMAIL")
        binding.etEmail.setText(emailGmail)

        binding.btnRegistrasi.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString()

            var isValid = true

            // Validasi 1: Semua wajib diisi
            if (nama.isEmpty()) { binding.tilNama.error = "Nama wajib diisi"; isValid = false }
            if (username.isEmpty()) { binding.tilUsername.error = "Username wajib diisi"; isValid = false }
            if (password.isEmpty()) { binding.tilPassword.error = "Password wajib diisi"; isValid = false }

            // Validasi 2: Password minimal 6 karakter
            if (password.isNotEmpty() && password.length < 6) {
                binding.tilPassword.error = "Password minimal 6 karakter"
                isValid = false
            }

            // Validasi 3: Username tidak boleh mengandung spasi
            if (username.contains(" ")) {
                binding.tilUsername.error = "Username tidak boleh mengandung spasi"
                isValid = false
            }

            if (isValid) {
                // Simpan ke SharedPreferences
                val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("REG_USERNAME", username)
                editor.putString("REG_PASSWORD", password)
                editor.putString("REG_NAMA", nama)
                editor.apply()

                // Tampilkan pesan/halaman informasi sukses
                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_LONG).show()

                // Selesai, kembali ke Login
                finish()
            }
        }
    }
}