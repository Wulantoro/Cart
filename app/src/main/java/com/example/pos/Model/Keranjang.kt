package com.example.pos.Model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.starkling.shoppingcart.models.Saleable
import kotlinx.android.parcel.Parcelize
@Entity(tableName = "keranjang")
//@Parcelize


class Keranjang {
    @PrimaryKey(autoGenerate = true)
    var idkeranjang = 0
    var idbarangk = 0
    var jumlahbelanja = 0
    var checkout = 0
    var total = 0
}

//data class Keranjang(@ColumnInfo(name = "idbarangk") var idbarangk: Long,
//                  @ColumnInfo(name = "jumlahbelanja") var jumlahbelanja: Long,
//                  @ColumnInfo(name = "total") var total: Long,
//                     @ColumnInfo(name = "checkout") var checkout: Int,
//                  @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idkeranjang") var idkeranjang: Long = 0): Parcelable





