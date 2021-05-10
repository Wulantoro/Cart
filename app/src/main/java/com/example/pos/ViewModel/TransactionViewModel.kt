package com.example.pos.ViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.pos.Keranjang
import com.example.pos.Repo.KeranjangRepository


class TransactionViewModel @ViewModelInject constructor(private val keranjangRepository: KeranjangRepository) : BaseViewModel() {

     private var mTriggerFetchdata = MutableLiveData<Boolean>()
    private var mTriggerKeranjang = MutableLiveData<Long>()

    private var keranjang : LiveData<List<Keranjang>> = Transformations.switchMap(mTriggerFetchdata){
        keranjangRepository.getAllData()
    }




     fun insertKeranjang(keranjang: Keranjang) {
         keranjangRepository.insertKeranjang(keranjang)

     }


    fun triggerFetchKeranjang(idkeranjang: Long) {
        mTriggerKeranjang.value = idkeranjang
    }

    fun getKeranjangById() = keranjang

    fun getAllKeranjang(): LiveData<List<Keranjang>> {
        return keranjang
    }

    fun loadKeranjang() {
        mTriggerFetchdata.value = true
    }

 }

