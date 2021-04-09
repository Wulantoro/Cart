package com.example.pos

import android.os.Parcelable

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity(tableName = "barang")
@Parcelize


data class Barang(@ColumnInfo(name = "namabarang") var namabarang: String,
                  @ColumnInfo(name = "hargabarang") var hargabarang: String,
                  @ColumnInfo(name = "stockbarang") var stockbarang: String,
                  @ColumnInfo(name = "selectedQuantity") var selectedQuantity: Int = 0,
                  @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idbarang") var idbarang: Long = 0): Parcelable



