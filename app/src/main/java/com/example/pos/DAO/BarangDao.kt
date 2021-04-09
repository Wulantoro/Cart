package com.example.pos.DAO

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.pos.Barang
import io.reactivex.Flowable

@Dao
interface BarangDao {
    @Insert(onConflict = REPLACE)
    fun tambahBarang(barang: Barang)

    @Query("UPDATE barang SET namabarang =:namabarang, hargabarang=:hargabarang, stockbarang=:stockbarang WHERE idbarang =:idbarang")
    fun updateBarang(idbarang: Long, namabarang:String, hargabarang: String, stockbarang: String)

    @Delete
     fun deleteBarang(barang: Barang)

    @Query("SELECT * from barang ORDER BY idbarang DESC")
    fun getAll(): Flowable<List<Barang>>

    @Query("SELECT * FROM barang WHERE idbarang = :idbarang ")

    fun getById(idbarang: String) : Flowable<Barang>

}