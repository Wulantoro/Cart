package com.example.pos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pos.DAO.BarangDao
import com.example.pos.DAO.KeranjangDao
import com.example.pos.Model.Keranjang


@Database(
    entities = [Barang::class, Keranjang::class], version = 5, exportSchema = false

)
abstract class BarangDatabase : RoomDatabase() {
    abstract fun getBarangDao(): BarangDao
    abstract fun getKeranjangDao(): KeranjangDao

}