package com.example.pos.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.pos.Barang

import com.example.pos.Model.Keranjang
import io.reactivex.Flowable

@Dao
interface KeranjangDao {

//    @Insert(onConflict = REPLACE)
//    fun insertBatch(keranjang... Keranjang)

    @Insert(onConflict = REPLACE)
    fun tambahKeranjang(keranjang: Keranjang)

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg entity: Keranjang)


    @Query("UPDATE keranjang SET idbarangk =:idbarangk, jumlahbelanja=:jumlahbelanja, total=:total WHERE idkeranjang =:idkeranjang")
    fun updateKeranjang(idkeranjang: Long, idbarangk:Long, jumlahbelanja: Long, total: Long)

    @Delete
    fun deleteKeranjang(keranjang: Keranjang)

    @Query("SELECT * from keranjang ORDER BY idkeranjang DESC LIMIT 1")
    fun getAll(): Flowable<List<Keranjang>>

    @Query("SELECT * FROM keranjang WHERE idkeranjang = :idkeranjang ")
    fun getById(idkeranjang: Long) : Flowable<Keranjang>

}