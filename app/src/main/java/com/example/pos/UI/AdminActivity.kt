package com.example.pos.UI

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.*
import com.example.pos.Adapter.BarangAdapter
import com.example.pos.Base.BaseActivity
import com.example.pos.Barang
import com.example.pos.ViewModel.AdminActivityViewModel
import com.example.pos.databinding.ActivityAdminBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.input_dialog.view.*
//import com.google.android.material.floatingactionbutton.FloatingActionButton

@AndroidEntryPoint
class AdminActivity : BaseActivity<ActivityAdminBinding, AdminActivityViewModel>() {

    override fun getViewModelBindingVariable(): Int {
        return NO_VIEW_MODEL_BINDING_VARIABLE
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_admin
    }

    private val barangs : ArrayList<Barang> = ArrayList()
    lateinit var adapter: BarangAdapter
    private val viewModel: AdminActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataBinding().notesRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = BarangAdapter(barangs, this, viewModel )
        getDataBinding().notesRV.adapter = adapter

        viewModel.getAllbarang().observe(this, Observer<List<Barang>> {
            barangs.clear()
            barangs.addAll(it!!)
            adapter.notifyDataSetChanged()
        })

        viewModel.loadbarang()

        addNoteFAB.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.input_dialog, null)
            dialogBuilder.setView(view)
            dialogBuilder.setTitle("Masukkan data baru")
            val etnmbarang = view.etnmbarang
            val ethrgbarang = view.ethrgbarang
            val etjumlah = view.etjumlah
            dialogBuilder.setPositiveButton("Tambahkan") { _: DialogInterface, _: Int ->
                val etnmbarang = etnmbarang.text
                val ethrgbarang = ethrgbarang.text
                val etjumlah = etjumlah.text

                viewModel.insertBarang(Barang(etnmbarang.toString(), ethrgbarang.toString(),  etjumlah.toString()))
                applicationContext.toast("Data berhasil dimasukkan")
            }
            dialogBuilder.setNegativeButton("Batal") { _: DialogInterface, _: Int ->
            }
            dialogBuilder.show()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadbarang()
    }

    fun Context.toast(message:CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

//    override fun onResume() {
//        super.onResume()
//        loaddata()
//    }

//    private fun loaddata() {
//        CoroutineScope(Dispatchers.IO).launch {
////            barangAdapter.setData(db.getBarangDao().getAllBarang())
//            withContext(Dispatchers.Main) {
//                barangAdapter.notifyDataSetChanged()
//            }
//        }
//    }
//
//    private fun setupView() {
//        supportActionBar!!.apply {
//            title = "Barang"
//        }
//    }
//
//    private fun setupListener() {
//        addNoteFAB.setOnClickListener {
//            intentEdit(Constant.TYPE_CREATE, 0)
//        }
//    }
//
//    private fun setupRecyclerView() {
//        barangAdapter = BarangAdapter(
//                arrayListOf(),
//                object : BarangAdapter.OnAdapterListener {
//                    override fun onDelete(barang: Barang) {
//                        deleteAlert(barang)
//                    }
//
//                    override fun onUpdate(barang: Barang) {
//                        intentEdit(Constant.TYPE_UPDATE, barang.id_barang)
//                    }
//                }
//        )
//
//        notesRV.apply {
//            layoutManager = LinearLayoutManager(applicationContext)
//            adapter = barangAdapter
//        }
//    }
//
//    private fun intentEdit(intent_type: Int, id_barang: Int?) {
//        startActivity(
//                Intent(this, UpdateActivity::class.java)
//                        .putExtra("intent_type", intent_type)
//                        .putExtra("id_barang", id_barang)
//        )
//    }
//
//    private fun deleteAlert(barang: Barang) {
//        val dialog = AlertDialog.Builder(this)
//        dialog.apply {
//            setTitle("Konfirmasi Hapus")
//            setMessage("Yakin hapus ${barang.nama_barang}?")
//            setNegativeButton("Batal") { dialogInterface, i ->
//                dialogInterface.dismiss()
//            }
//            setPositiveButton("Hapus") { dialogInterface, i ->
//                CoroutineScope(Dispatchers.IO).launch {
////                    db.getBarangDao().deleteBarang(barang)
//                    dialogInterface.dismiss()
//                    loaddata()
//                }
//            }
//        }
//        dialog.show()
//    }

//}