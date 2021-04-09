package com.example.pos.ViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.pos.Barang
import com.example.pos.BarangRepository

class UpdateViewModel @ViewModelInject constructor(private val barangRepository: BarangRepository) : BaseViewModel() {

    private var mTrigger: MutableLiveData<String> = MutableLiveData()
    private var barang: LiveData<Barang> = Transformations.switchMap(mTrigger) {
        barangRepository.getDataById(it)
    }

    fun getBarangById() = barang

    fun triggerFetchdata(idbarang: String) {
        mTrigger.value = idbarang
    }

    fun updateBarang(idbarang: Long,  nama_barang: String, harga_barang: String, stock_barang: String) {
        barangRepository.updateBarang(idbarang,  nama_barang, harga_barang, stock_barang)


    }
}