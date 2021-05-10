package com.example.pos.DAO

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

import com.example.pos.Keranjang
import io.reactivex.Flowable

@Dao
interface KeranjangDao {

//    @Insert(onConflict = REPLACE)
//    fun insertBatch(keranjang... Keranjang)

    @Insert(onConflict = REPLACE)
    fun tambahKeranjang(keranjang: Keranjang)

//    @Insert(onConflict = REPLACE)
//    fun insertAll(vararg entity: Keranjang)


    @Query("UPDATE keranjang SET jumlahbelanja=:jumlahbelanja, total=:total WHERE idkeranjang =:idkeranjang")
    fun updateKeranjang(idkeranjang: Long, jumlahbelanja: Long, total: Long)

    @Delete
    fun deleteKeranjang(keranjang: Keranjang)

    @Query("SELECT * from keranjang ")
    fun getAll(): Flowable<List<Keranjang>>

    @Query("SELECT * FROM keranjang WHERE idkeranjang = :idkeranjang ")
    fun getById(idkeranjang: Long) : Flowable<Keranjang>


    /*************************/
    @Transaction
    open fun updateData(keranjangs: List<Keranjang>) {

        insertAll(keranjangs)
    }
    @Insert
     fun insertAll(keranjang: List<Keranjang>)


//     fun multipleInsert(List<Keranjang>)

}