package com.example.there_help.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pengaduan_warga")
data class PengaduanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,
    val kategori: String,
    val isiPengaduan: String,
    val createdAt: Long
)