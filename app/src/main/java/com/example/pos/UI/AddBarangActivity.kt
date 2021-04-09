package com.example.pos.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.pos.*
import com.example.pos.Utils.UtilExtensions.myToast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddBarangActivity : AppCompatActivity() {

//    private lateinit var viewModel: BarangViewModel
//    private lateinit var barangDatabase: BarangDatabase
//    private lateinit var repository: BarangRepository
//    private lateinit var factory: BarangViewModelFactory
//
//    private lateinit var etnmbarang : TextInputEditText
//    private lateinit var ethrgbarang : TextInputEditText
//    private lateinit var etjumlah : TextInputEditText
//    private lateinit var btnsave : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_barang)
//
//        etnmbarang = findViewById(R.id.etnmbarang)
//        ethrgbarang = findViewById(R.id.ethrgbarang)
//        etjumlah = findViewById(R.id.etjumlah)
//        btnsave = findViewById(R.id.btnsave)


//        barangDatabase = BarangDatabase(this)
//        repository = BarangRepository(barangDatabase)
//        factory = BarangViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, factory)[BarangViewModel::class.java]

//        btnsave.setOnClickListener {
////            insertData()
//        }

    }

//    private fun insertData() {
//        val nama_barang = etnmbarang.text.toString()
//        val harga_barang = ethrgbarang.text.toString()
//        val stock_barang = etjumlah.text.toString()
//
//
//        //why id null? because id is auto generate
//        val barang = Barang(id_barang = null, nama_barang = nama_barang, harga_barang = harga_barang, stock_barang = stock_barang)
//        CoroutineScope(Dispatchers.Main).launch {
////            viewModel.tambahBarang(barang).also {
////                myToast("Succes Tambah Barang")
//
////            }
//        }
//    }
}