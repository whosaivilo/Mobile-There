package com.example.there_help

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_help.data.AppDatabase
import com.example.there_help.data.entity.PengaduanEntity
import com.example.there_help.databinding.ActivityRingkasanPengaduanBinding
import kotlinx.coroutines.launch

class RingkasanPengaduanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRingkasanPengaduanBinding
    private lateinit var db: AppDatabase
    private lateinit var adapter: PengaduanAdapter
    private val listData = mutableListOf<PengaduanEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRingkasanPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Inisialisasi Database & Adapter
        db = AppDatabase.getInstance(this)
        adapter = PengaduanAdapter(listData, this)

        // 2. Setup RecyclerView
        binding.rvDaftarPengaduan.layoutManager = LinearLayoutManager(this)
        binding.rvDaftarPengaduan.adapter = adapter

        // 3. Tambahkan Garis Pemisah (DividerItemDecoration) sesuai Silabus Pertemuan 12
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rvDaftarPengaduan.addItemDecoration(dividerItemDecoration)

        binding.fabAddPengaduan.setOnClickListener {
            startActivity(Intent(this, FormPengaduanActivity::class.java))
        }
    }

    // Gunakan onResume agar data selalu refresh saat halaman dibuka
    override fun onResume() {
        super.onResume()
        fetchData()
    }

    // Fungsi READ (Mengambil data dari Room dengan Coroutines)
    private fun fetchData() {
        lifecycleScope.launch {
            val data = db.pengaduanDao().getAll() // Pastikan DAO Anda punya fungsi getAll()
            listData.clear()
            listData.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    // Fungsi DELETE (Dipanggil dari Adapter)
    fun deletePengaduan(pengaduan: PengaduanEntity) {
        lifecycleScope.launch {
            db.pengaduanDao().delete(pengaduan)
            fetchData() // Refresh layar setelah dihapus
            Toast.makeText(this@RingkasanPengaduanActivity, "Laporan dihapus!", Toast.LENGTH_SHORT).show()
        }
    }
}