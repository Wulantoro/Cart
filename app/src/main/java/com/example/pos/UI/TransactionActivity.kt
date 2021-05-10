package com.example.pos.UI

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.*
import com.example.pos.Adapter.TransactionAdapter
import com.example.pos.Base.BaseActivity
import com.example.pos.Keranjang
import com.example.pos.ViewModel.TransactionViewModel
import com.example.pos.databinding.ActivityTransactionBinding

import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class TransactionActivity : BaseActivity<ActivityTransactionBinding, TransactionViewModel>() {

    override fun getViewModelBindingVariable(): Int {
        return NO_VIEW_MODEL_BINDING_VARIABLE
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_transaction
    }


    private val keranjangs : ArrayList<Keranjang> = ArrayList()

    lateinit var transactionAdapter: TransactionAdapter

    private val viewModel: TransactionViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getDataBinding().rvSelectedProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        transactionAdapter = TransactionAdapter(keranjangs, this)
        getDataBinding().rvSelectedProduct.adapter = transactionAdapter

        viewModel.getAllKeranjang().observe(this, androidx.lifecycle.Observer<List<Keranjang>> {
            keranjangs.clear()
            keranjangs.addAll(it!!)
            transactionAdapter.notifyDataSetChanged()
        })

        viewModel.loadKeranjang()

    }



    fun Context.toast(message:CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}