package com.example.pos.Model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.starkling.shoppingcart.models.Saleable
import kotlinx.android.parcel.Parcelize
@Entity(tableName = "keranjang")
@Parcelize

data class Keranjang(@ColumnInfo(name = "idbarangk") var idbarangk: Long,
                  @ColumnInfo(name = "jumlahbelanja") var jumlahbelanja: Long,
                  @ColumnInfo(name = "total") var total: Long,
                     @ColumnInfo(name = "checkout") var checkout: Int,
                  @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idkeranjang") var idkeranjang: Long = 0): Parcelable





