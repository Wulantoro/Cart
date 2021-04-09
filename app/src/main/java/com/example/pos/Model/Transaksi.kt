package com.example.pos.Model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Date

@Entity(tableName = "transaksi")
@Parcelize

data class Transaksi(@ColumnInfo(name = "idBarang")var idBarang: Long,
                     @ColumnInfo(name = "tanggal")var tanggal: Date,
                     @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idTransaksi")var idTransaksi: Long = 0): Parcelable
