package com.example.there_help.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_help.databinding.ItemBeritaBinding

class BeritaAdapter(
    private val beritaList: List<BeritaModel>,
    private val onItemClick: (BeritaModel) -> Unit
) : RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    inner class BeritaViewHolder(val binding: ItemBeritaBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding = ItemBeritaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BeritaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val item = beritaList[position]
        with(holder.binding) {
            tvJudulBerita.text = item.judul
            tvTanggalBerita.text = item.tanggal
            tvDescBerita.text = item.deskripsi

            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgBerita)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = beritaList.size
}