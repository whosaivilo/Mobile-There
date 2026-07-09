package com.example.there_help.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.there_help.data.entity.PengaduanEntity

@Dao
interface PengaduanDao {
    @Query("SELECT * FROM pengaduan_warga")
    suspend fun getAll(): List<PengaduanEntity>

    @Insert
    suspend fun insert(pengaduan: PengaduanEntity)

    @Delete
    suspend fun delete(pengaduan: PengaduanEntity)
}