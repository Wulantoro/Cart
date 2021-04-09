package com.example.pos.ViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.pos.Barang
import com.example.pos.BarangRepository

class AdminActivityViewModel @ViewModelInject constructor(private val barangRepository: BarangRepository) : BaseViewModel() {

    private var mTriggerFetchdata = MutableLiveData<Boolean>()
    private var barang : LiveData<List<Barang>> = Transformations.switchMap(mTriggerFetchdata){
        barangRepository.getAllData()
    }

    fun insertBarang(barang: Barang) {
        barangRepository.insertbarang(barang)
    }

    fun getAllbarang(): LiveData<List<Barang>> {
        return barang
    }

    fun loadbarang() {
        mTriggerFetchdata.value = true
    }

    fun delettebarang(barang: Barang) {
        barangRepository.deleteBarang(barang)
    }
}