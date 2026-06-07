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
            val email = binding.etInputGmail.text.toString().trim()

            // Validasi: Kosong ATAU tidak diakhiri dengan @gmail.com
            if (email.isEmpty() || !email.endsWith("@gmail.com")) {

                // Tampilkan error dengan MaterialAlertDialog sesuai Soal 1
                MaterialAlertDialogBuilder(this)
                    .setTitle("Validasi Gagal")
                    .setMessage("Email tidak boleh kosong dan wajib menggunakan domain @gmail.com!")
                    .setPositiveButton("Mengerti") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()

            } else {
                // Jika valid, lempar ke RegisterActivity dan bawa datanya
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra("EMAIL_GMAIL", email)
                startActivity(intent)
                finish() // Tutup halaman input email
            }
        }
    }
}