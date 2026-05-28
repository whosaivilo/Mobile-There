package com.example.there_help.Home



import android.os.Bundle
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.there_help.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHelpBinding

    private val dataListWithDesc = listOf(
        mapOf("title" to "Kebijakan Privasi", "desc" to "Baca aturan privasi data warga"),
        mapOf("title" to "Syarat Ketentuan", "desc" to "Aturan penggunaan aplikasi Suara Warga"),
        mapOf("title" to "FAQ", "desc" to "Pertanyaan yang sering diajukan warga"),
        mapOf("title" to "Hubungi Admin", "desc" to "Kontak tim pengembang Bina Desa"),
        mapOf("title" to "Versi Aplikasi", "desc" to "Bina Desa v1.0.0")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Pusat Bantuan"

        // Tombol back di Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed() // Kembali ke halaman Home
        }


        val adapter = SimpleAdapter(
            this,
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.listViewItems.adapter = adapter

        // Aksi klik item
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            Toast.makeText(this, "Membuka: $title", Toast.LENGTH_SHORT).show()
        }
    }
}