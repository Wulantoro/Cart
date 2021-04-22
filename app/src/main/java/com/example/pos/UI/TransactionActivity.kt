package com.example.pos.UI

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.*
import com.example.pos.Adapter.TransactionAdapter
import com.example.pos.Base.BaseActivity
import com.example.pos.Barang
import com.example.pos.Model.Keranjang
import com.example.pos.ViewModel.KasirActivityViewModel
import com.example.pos.ViewModel.TransactionViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_transaction.*
import kotlinx.android.synthetic.main.content_transaction.*
import kotlinx.android.synthetic.main.list_item_selected.view.*
import java.util.*
import java.util.Objects.requireNonNull

class TransactionActivity :  AppCompatActivity() {

    private var items: ArrayList<Barang>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

//        items = Objects.requireNonNull(this.intent.extras)?.getParcelableArrayList("cart")

        val transactionAdapter = TransactionAdapter(items!!)
        rv_selected_product.apply {
            layoutManager = LinearLayoutManager(this@TransactionActivity)
            adapter = transactionAdapter
           transactionAdapter.notifyDataSetChanged()
        }

    }

}


//class TransactionActivity :  AppCompatActivity() {
//
//    private var items: ArrayList<Barang>? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_transaction)
//
//
//
//        items = Objects.requireNonNull(this.intent.extras)?.getParcelableArrayList("cart")
////        items = intent.getParcelableArrayListExtra("cart")
//
//        val idbarang = intent.getStringExtra("cart")
//
//
//        Log.e("Tag", items.toString())
//
////        val listHeroes = listOf(
////            Barang(namabarang = "Thor", hargabarang = "", stockbarang = "1"),
////            Barang(namabarang = "Captain America", hargabarang = "", stockbarang = "1"),
////            Barang(namabarang = "Iron Man", hargabarang = "", stockbarang = "1")
////        )
//
////        val transactionAdapter = TransactionAdapter(listHeroes)
//        val transactionAdapter = TransactionAdapter(items!!)
//        rv_selected_product.apply {
//            layoutManager = LinearLayoutManager(this@TransactionActivity)
//            adapter = transactionAdapter
//           transactionAdapter.notifyDataSetChanged()
//        }
//
////        mRecyclerView?.setLayoutManager(LinearLayoutManager(this))
////        val adapter: TransactionAdapter = TransactionAdapter(items!!)
////        mRecyclerView?.setAdapter(adapter)
////        adapter.notifyDataSetChanged()
//
//        var price = 0
//        for (i in items!!) {
//            price += i.hargabarang.toInt() * i.selectedQuantity
//        }
//        totprice.setText("Rp. $price")
//
//    }
//
//}


//@AndroidEntryPoint
//class TransactionActivity : BaseActivity<ActivityTransactionBinding, TransactionViewModel>() {
//
//    override fun getViewModelBindingVariable(): Int {
//        return NO_VIEW_MODEL_BINDING_VARIABLE
//    }
//
//    override fun getLayoutId(): Int {
//        return R.layout.activity_transaction
//    }
//
//    private lateinit var idbarang: String
//    lateinit var barang: Barang
////    lateinit var keranjang: Keranjang
//
//    private val keranjang : MutableLiveData<Keranjang> by lazy {
////        ViewModelProvider(this).get(keranjang::class.java)
//        MutableLiveData<Keranjang>()
//    }
//
////    private var items: ArrayList<Barang>? = null
//    private lateinit var items: List<Barang>
//
//    private val barangs : ArrayList<Barang> = ArrayList()
//
//    private val keranjangs : ArrayList<Keranjang> = ArrayList()
//
//    lateinit var transactionAdapter: TransactionAdapter
//
//    private val viewModel: TransactionViewModel by viewModels()
//    private val kasirViewModel: KasirActivityViewModel by viewModels()
//
////    private var mRecyclerView: RecyclerView? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
////        mRecyclerView = findViewById(R.id.rv_selected_product)
//
//        items = requireNonNull(this.intent.extras?.getParcelableArrayList("cart"))!!
//
//        rv_selected_product.setLayoutManager(LinearLayoutManager(this))
//        val adapter: TransactionAdapter = TransactionAdapter(items)
//        rv_selected_product.setAdapter(adapter)
//        adapter.notifyDataSetChanged()
//
//
////       rv_selected_product.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
////        val adapter: TransactionAdapter = TransactionAdapter(items)
////
////
////        transactionAdapter = TransactionAdapter(items)
////        rv_selected_product.adapter = transactionAdapter
////        transactionAdapter.notifyDataSetChanged()
//        var price = 0
//        for (i in items!!) {
//            price += i.hargabarang.toInt() * i.selectedQuantity
//        }
//        totprice.setText("Rp. $price")
//
//
//
////        rv_selected_product.apply {
////            layoutManager = LinearLayoutManager(this@TransactionActivity)
////            adapter = KeranjangAdapter(mutableListOf(), this@TransactionActivity, viewModel)
////        }
//
////        idbarang = intent.getLongExtra("idbarang", 0).toString()
////        Log.e("TAG", "id barang = " + idbarang)
////
////        viewModel.getBarangById().observe(this, Observer {
////            barang = it!!
////            displayBarang(it)
////        })
////        viewModel.triggerFetchdata(idbarang)
////
////        product_decrement.setOnClickListener {
////            viewModel.decrementQuantityProduct(barang)
////            Toast.makeText(this, "barang = " + barang.selectedQuantity, Toast.LENGTH_SHORT).show()
////
////        }
////
////        product_increment.setOnClickListener {
////            viewModel.incrementQuantityProduct(barang)
////            Toast.makeText(this, "barang = " + barang.selectedQuantity, Toast.LENGTH_SHORT).show()
////        }
//
//
//
//
//
////        viewModel.getKeranjangById().observe(this, Observer {
////            keranjang = it!!
////            btnchectout.text = "Checkout (${it.total})  Total Amount: ${it.jumlahbelanja}.R"
//////            displayKeranjang(it)
////        })
//
////        viewModel.getAllKeranjang().observe(this, Observer {
//////            keranjang = it!!
////            btnchectout.text = "Checkout (${it.total})  Total Amount: ${it.jumlahbelanja}.R"
////            Log.e("TAG"+ "Checkout (${it.total})"," Total Amount: ${it.jumlahbelanja}.R")
////
////        })
//
////        counterView.addCounterValueChangeListener(object : CounterView.CounterValueChangeListener{
////            override fun onValueAdd(count: Int) {
////                val tvhargabrg = tvhargabrg.text
////
//////                barang.stockbarang = count.toString()
//////                keranjang = it!!
//////                keranjang.total = count.toLong()
////                val jml = tvhargabrg.toString().toLong() * count
////                val viewModel: TransactionViewModel? = null
////                viewModel?.insertKeranjang(Keranjang(idbarang.toLong(), jml, count.toLong() ))
////
////                Log.e("jumlah belanja", jml.toString())
////                Log.e("TAG" , tvhargabrg.toString())
////
////                applicationContext.toast(count.toString())
//////                viewModel?.getAllKeranjang(btnchectout.text = "Checkout (${keranjang.total})  Total Amount: ${keranjang.jumlahbelanja}.R")
////                viewModel?.getKeranjangById()?.observe( this@TransactionActivity, Observer {
////
////                    btnchectout.text = "Checkout (${it.total})  Total Amount: ${it.jumlahbelanja}.R"
////                    Log.e("TAG"+ "Checkout (${it.total})"," Total Amount: ${it.jumlahbelanja}.R")
////                })
////                viewModel?.triggerFetchKeranjang(idkeranjang = 1)
////                Log.e("keranjang " , keranjang.value?.jumlahbelanja.toString())
////
////
////            }
////
////            override fun onValueDelete(count: Int) {
////                barang.stockbarang = count.toString()
////                applicationContext.toast(count.toString())
////            }
////
////        })
//
//    }
//
//
//
//    fun displayBarang(barang: Barang?) {
//        getDataBinding().layoutTrans.barang = barang
//
//        Log.e("TAG", "nama barang = " + barang?.namabarang)
//    }
//
////    fun displayKeranjang(keranjang: Keranjang) {
////        getDataBinding().layoutTrans.keranjang = keranjang
////    }
//
//    fun Context.toast(message:CharSequence) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
//}