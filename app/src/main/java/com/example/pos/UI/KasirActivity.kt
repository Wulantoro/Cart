package com.example.pos.UI

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.*
import com.example.pos.Adapter.KasirAdapter
import com.example.pos.Adapter.KeranjangAdapter
import com.example.pos.Base.BaseActivity
import com.example.pos.Barang
import com.example.pos.DAO.KeranjangDao
import com.example.pos.Model.Keranjang
import com.example.pos.Utils.Converter
import com.example.pos.ViewModel.KasirActivityViewModel
import com.example.pos.databinding.ActivityKasirBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_kasir.*
import kotlinx.android.synthetic.main.activity_kasir.view.*
import kotlinx.android.synthetic.main.bottom_sheet_cart.*
import kotlinx.android.synthetic.main.input_dialog.view.*
import kotlinx.android.synthetic.main.item_barang.view.*
import kotlinx.android.synthetic.main.item_barang_kasir.*
import kotlinx.android.synthetic.main.list_item_selected.*
import kotlinx.android.synthetic.main.number_picker.*

@AndroidEntryPoint
 class KasirActivity : BaseActivity<ActivityKasirBinding, KasirActivityViewModel>() {

    override fun getViewModelBindingVariable(): Int {
        return NO_VIEW_MODEL_BINDING_VARIABLE
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_kasir
    }

    private val barangs: ArrayList<Barang> = ArrayList()

    private val keranjangs: ArrayList<Keranjang> = ArrayList()

    private var selected: MutableList<Barang> = ArrayList()


    lateinit var kasirAdapter: KasirAdapter

    private val viewModel: KasirActivityViewModel by viewModels()

//     abstract val keranjangDao: KeranjangDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getDataBinding().rvbarang.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        kasirAdapter = KasirAdapter(barangs, keranjangs, viewModel, this)
        getDataBinding().rvbarang.adapter = kasirAdapter

        viewModel.getAllbarang().observe(this, Observer<List<Barang>> {
            barangs.clear()
            barangs.addAll(it!!)
            kasirAdapter.notifyDataSetChanged()
        })

        viewModel.loadbarang()

        rv_selected_product.apply {
            layoutManager = LinearLayoutManager(this@KasirActivity)
            adapter = KeranjangAdapter(mutableListOf(), this@KasirActivity, viewModel)
        }

        viewModel.listenToSelectedProduct().observe(this, Observer {

            rv_selected_product.adapter?.let { a ->
                if (a is KeranjangAdapter) {
                    a.updateList(it)
                }
            }

            val totalPrice = if (it.isEmpty()) {
                0
            } else {
                it.sumBy { p ->
                    p.hargabarang.toInt() * p.selectedQuantity!!
                }
            }
            total_price.text = Converter.rupiah(totalPrice.toDouble())
//            product_selectedQuantity.text = p.selectedQuantity.toString()

        })


        btn_checkout.setOnClickListener { view: View? ->

            InsertCartItem().execute()



//
//            val  tvhargabrg = tvhargabrg.text
//            val cart_product_quantity_tv = cart_product_quantity_tv.text
//            val jumlahbelanja = tvhargabrg.toString().toLong() *  cart_product_quantity_tv.toString().toLong()

//            Toast.makeText(this, "jumlah belanja = " + jumlahbelanja, Toast.LENGTH_LONG).show()




//           val listHeroes = listOf(
//            Barang(namabarang = "Thor", hargabarang = "", stockbarang = "1"),
//            Barang(namabarang = "Captain America", hargabarang = "", stockbarang = "1"),
//            Barang(namabarang = "Iron Man", hargabarang = "", stockbarang = "1")
//        )
//
//           val coba = "coba coba"


//            val item = selected as ArrayList<Barang>
//            val intent = Intent(this, TransactionActivity::class.java)
//            val intent = Intent(this, TransactionActivity::class.java).apply {
//                val b = Bundle()
//                b.putParcelableArrayList("cart", item)
//                intent.putExtras(b)
//            }
//            val b = Bundle()
//            b.putParcelableArrayList("cart", item)
//            intent.putExtras(b)
//            startActivity(intent)

//           Log.e("TAG", "item = " + item.toString())
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class InsertCartItem : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {

            val tvhargabrg = tvhargabrg.text
            val cart_product_quantity_tv = cart_product_quantity_tv.text
            val jumlahbelanja =
                tvhargabrg.toString().toLong() * cart_product_quantity_tv.toString().toLong()

            for (i in barangs!!) {
                val k = Keranjang()
                k.idbarangk = i.idbarang.toInt()
                k.checkout = 0
                k.total = cart_product_quantity_tv.toString().toInt()
                k.jumlahbelanja = jumlahbelanja.toInt()

//                keranjangDao.insertAll(k)



                viewModel.transaksi(k)

            }

            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            Toast.makeText(this@KasirActivity, "Order Placed Successfully", Toast.LENGTH_SHORT)
                .show()

//            val intent = Intent(this, TransactionActivity::class.java)
////
//            startActivity(intent)


//            finish()
        }
    }
//

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
////        menuInflater.inflate(R.menu.main_menu, menu)
////        return super.onCreateOptionsMenu(menu)
//
//        val inflater = menuInflater
//        inflater.inflate(R.menu.main_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        if (id == R.id.cart) {
//            val item1 = selected as java.util.ArrayList<Barang>
//            val intent = Intent(this, TransactionActivity::class.java)
//            val b = Bundle()
//            b.putParcelableArrayList("cart", item1)
//            intent.putExtras(b)
//            startActivity(intent)
//        }
//        return super.onOptionsItemSelected(item)
//    }


    init {

        selected = ArrayList()

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadbarang()

    }

}



