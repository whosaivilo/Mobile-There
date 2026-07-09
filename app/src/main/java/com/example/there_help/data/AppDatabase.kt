package com.example.there_help.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.there_help.data.dao.PengaduanDao
import com.example.there_help.data.entity.PengaduanEntity

@Database(entities = [PengaduanEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pengaduanDao(): PengaduanDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bina_desa_database"
                )
                    .fallbackToDestructiveMigration() // Mencegah crash kalau ada perubahan tabel
                    .build().also { INSTANCE = it }
            }
        }
    }
}