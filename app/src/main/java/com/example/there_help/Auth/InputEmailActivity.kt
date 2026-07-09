package com.example.there_help.Auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityInputEmailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class InputEmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLanjutRegis.setOnClickListener {
            // 1. Ambil ketikan pengguna (hanya nama depannya saja)
            val inputPrefix = binding.etInputGmail.text.toString().trim()

            if (inputPrefix.isEmpty()) {
                binding.etInputGmail.error = "Email wajib diisi"
                return@setOnClickListener
            }

            // 2. Trik Opsional: Mencegah error jika pengguna "bandel" tetap mengetik @gmail.com
            // Ini akan menghapus @gmail.com jika pengguna terlanjur mengetiknya
            val prefixBersih = inputPrefix.removeSuffix("@gmail.com")

            // 3. Gabungkan nama depan dengan domain secara manual
            val emailFull = "$prefixBersih@gmail.com"

            // 4. Kirim email yang sudah utuh ke RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("EMAIL_GMAIL", emailFull)
            startActivity(intent)
        }
    }
}