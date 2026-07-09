package com.example.there_help

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.there_help.data.entity.PengaduanEntity
import com.example.there_help.databinding.ItemPengaduanBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PengaduanAdapter(
    private val listPengaduan: List<PengaduanEntity>,
    private val activity: RingkasanPengaduanActivity // Melempar referensi activity untuk fungsi delete
) : RecyclerView.Adapter<PengaduanAdapter.PengaduanViewHolder>() {

    inner class PengaduanViewHolder(val binding: ItemPengaduanBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengaduanViewHolder {
        val binding = ItemPengaduanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PengaduanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PengaduanViewHolder, position: Int) {
        val pengaduan = listPengaduan[position]

        with(holder.binding) {
            tvNamaPengaduList.text = pengaduan.nama
            tvKategoriList.text = pengaduan.kategori
            tvIsiPengaduanList.text = pengaduan.isiPengaduan

            holder.itemView.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, DetailPengaduanActivity::class.java).apply {
                    // Lempar data spesifik dari item yang diklik
                    putExtra("EXTRA_NAMA", pengaduan.nama)
                    putExtra("EXTRA_KATEGORI", pengaduan.kategori)
                    putExtra("EXTRA_ISI", pengaduan.isiPengaduan)
                }
                context.startActivity(intent)
            }

            // Fitur Hapus Data dengan Konfirmasi
            btnDeletePengaduan.setOnClickListener {
                MaterialAlertDialogBuilder(holder.itemView.context)
                    .setTitle("Hapus Laporan")
                    .setMessage("Apakah Anda yakin ingin menghapus laporan dari ${pengaduan.nama}?")
                    .setPositiveButton("Hapus") { dialog, _ ->
                        activity.deletePengaduan(pengaduan) // Panggil fungsi delete di Activity
                        dialog.dismiss()
                    }
                    .setNegativeButton("Batal") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    override fun getItemCount(): Int = listPengaduan.size
}