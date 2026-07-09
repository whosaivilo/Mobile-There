package com.example.there_help.Home.pertemuan_10

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.there_help.R


class ListViewBeritaAdapter(context: Context, private val daftarBerita: List<BeritaModel>) :
    ArrayAdapter<BeritaModel>(context, R.layout.item_berita_lv, daftarBerita) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_berita_lv, parent, false)

        val berita = daftarBerita[position]

        val imgBerita: ImageView = view.findViewById(R.id.imgBeritaLv)
        val tvJudul: TextView = view.findViewById(R.id.tvJudulBeritaLv)
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggalBeritaLv)

        tvJudul.text = berita.judul
        tvTanggal.text = berita.tanggal

        // Menggunakan Glide untuk memuat gambar dari URL
        Glide.with(context)
            .load(berita.imageUrl)
            .placeholder(android.R.color.darker_gray)
            .into(imgBerita)

        return view
    }
}