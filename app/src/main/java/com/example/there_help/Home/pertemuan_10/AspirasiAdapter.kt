package com.example.thereapps.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.there_help.Home.pertemuan_10.AspirasiModel
import com.example.there_help.databinding.ItemAspirasiBinding

class AspirasiAdapter(
    private val listAspirasi: List<AspirasiModel>,
    private val onItemClick: (AspirasiModel) -> Unit
) : RecyclerView.Adapter<AspirasiAdapter.AspirasiViewHolder>() {

    // 1. ViewHolder dibuat super minimalis & variabel binding di-set jadi 'val'
    inner class AspirasiViewHolder(val binding: ItemAspirasiBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AspirasiViewHolder {
        val binding = ItemAspirasiBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AspirasiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AspirasiViewHolder, position: Int) {
        val item = listAspirasi[position]

        // 2. Pemetaan data langsung dieksekusi di sini pakai scope function 'with'
        with(holder.binding) {
            tvNamaWarga.text = item.namaWarga
            tvKategori.text = item.kategori
            tvIsiLaporan.text = item.isiLaporan
            tvTanggal.text = item.tanggal

            // 3. Set click listener pada root layout card aspirasi
            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = listAspirasi.size
}