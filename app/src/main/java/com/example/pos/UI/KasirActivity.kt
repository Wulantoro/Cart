package com.example.pos.UI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.*
import com.example.pos.Adapter.KasirAdapter
import com.example.pos.Adapter.KeranjangAdapter
import com.example.pos.Base.BaseActivity
import com.example.pos.Barang
import com.example.pos.DAO.KeranjangDao
import com.example.pos.Keranjang
import com.example.pos.Utils.Converter
import com.example.pos.ViewModel.KasirActivityViewModel
import com.example.pos.ViewModel.TransactionViewModel
import com.example.pos.databinding.ActivityKasirBinding
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

    private val transacViewMode: TransactionViewModel by viewModels()



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
            insert()
            val intent = Intent(this, TransactionActivity::class.java)

            startActivity(intent)



        }


    }

    fun insert() {
        val tvnamabrg = tvnamabrg.text.toString()
            val tvhargabrg = tvhargabrg.text.toString()
            val cart_product_quantity_tv = cart_product_quantity_tv.text.toString()
            val total = tvhargabrg.toString().toLong() * cart_product_quantity_tv.toLong()

            transacViewMode.insertKeranjang(Keranjang( cart_product_quantity_tv.toLong(), total, 0, tvnamabrg ))
            applicationContext.toast("Data berhasil dimasukkan")

        val intent = Intent(this, TransactionActivity::class.java)

        startActivity(intent)

    }



    init {

        selected = ArrayList()

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadbarang()

    }

    fun Context.toast(message:CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}



