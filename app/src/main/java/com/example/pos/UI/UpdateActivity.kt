package com.example.pos.UI

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.pos.*
import com.example.pos.Base.BaseActivity
import com.example.pos.Barang
import com.example.pos.ViewModel.UpdateViewModel
import com.example.pos.databinding.ActivityUpdateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_update.*

@AndroidEntryPoint
class UpdateActivity : BaseActivity<ActivityUpdateBinding, UpdateViewModel>() {

    override fun getViewModelBindingVariable(): Int {
        return NO_VIEW_MODEL_BINDING_VARIABLE
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_update
    }

    private lateinit var idbarang: String
    lateinit var barang: Barang

    private val viewModel: UpdateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idbarang = intent.getLongExtra("idbarang", 0).toString()
        Log.e("TAG", "id barang = " + idbarang)

        viewModel.getBarangById().observe(this, Observer {
            barang = it!!
            displayBarang(it)
        })
        viewModel.triggerFetchdata(idbarang)

        btnsave.setOnClickListener {

            val etnmbarang = etnmbarang.text
            val ethrgbarang = ethrgbarang.text
            val etjumlah = etjumlah.text

            viewModel.updateBarang(barang.idbarang, etnmbarang.toString(), ethrgbarang.toString(), etjumlah.toString())
            Toast.makeText(this, "Data berhasil diubah ", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    fun displayBarang(barang: Barang?) {
        getDataBinding().etnmbarang.setText(barang?.namabarang)
        getDataBinding().ethrgbarang.setText(barang?.hargabarang)
        getDataBinding().etjumlah.setText(barang?.stockbarang)
    }
}