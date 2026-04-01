package com.example.there_help

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Kalkulator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kalkulator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // 1. Inisialisasi Komponen Persegi
        val inputSisi = findViewById<EditText>(R.id.inputSisi)
        val btnHitungPersegi = findViewById<Button>(R.id.btnHitungPersegi)
        val tvHasilPersegi = findViewById<TextView>(R.id.tvHasilPersegi)

        // 2. Inisialisasi Komponen Balok
        val inputPanjang = findViewById<EditText>(R.id.inputPanjang)
        val inputLebar = findViewById<EditText>(R.id.inputLebar)
        val inputTinggi = findViewById<EditText>(R.id.inputTinggi)
        val btnHitungBalok = findViewById<Button>(R.id.btnHitungBalok)
        val tvHasilBalok = findViewById<TextView>(R.id.tvHasilBalok)

        // 3. Event Listener (onClick) Persegi
        btnHitungPersegi.setOnClickListener {
            val sisiStr = inputSisi.text.toString()
            if (sisiStr.isNotEmpty()) {
                val sisi = sisiStr.toDouble()
                val luas = sisi * sisi

                tvHasilPersegi.text = "Hasil Luas: $luas"


                Log.i("LogKalkulator", "Luas Persegi berhasil dihitung!")
                Toast.makeText(this, "Luas Persegi berhasil dihitung!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Sisi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
        // 4. Event Listener (onClick) Balok
        btnHitungBalok.setOnClickListener {
            val pStr = inputPanjang.text.toString()
            val lStr = inputLebar.text.toString()
            val tStr = inputTinggi.text.toString()

            if (pStr.isNotEmpty() && lStr.isNotEmpty() && tStr.isNotEmpty()) {
                val p = pStr.toDouble()
                val l = lStr.toDouble()
                val t = tStr.toDouble()

                val volume = p * l * t
                tvHasilBalok.text = "Hasil Volume: $volume"

                // Menampilkan ke Logcat dan memunculkan pesan Toast
                Log.i("LogKalkulator", "Hitung Balok berhasil. P:$p, L:$l, T:$t, Volume:$volume")
                Toast.makeText(this, "Volume Balok berhasil dihitung!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Semua input balok harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}