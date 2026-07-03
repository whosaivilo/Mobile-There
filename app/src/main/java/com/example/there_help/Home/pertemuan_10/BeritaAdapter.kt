package com.example.there_help.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_help.data.model.BeritaModel
import com.example.there_help.databinding.ItemBeritaBinding

class BeritaAdapter(private val items: List<BeritaModel>) : RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {
    inner class BeritaViewHolder(val binding: ItemBeritaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding = ItemBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeritaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvJudulBerita.text = item.title ?: "Tidak ada judul"
        holder.binding.tvDescBerita.text = item.description ?: "Tidak ada deskripsi"
        holder.binding.tvTanggalBerita.text = "9 Juni 2026"

        Glide.with(holder.itemView.context)
            .load(item.urlToImage)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(holder.binding.imgBerita)
    }

    override fun getItemCount(): Int = items.size
}