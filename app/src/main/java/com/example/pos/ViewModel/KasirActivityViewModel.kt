package com.example.pos.ViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.pos.Barang
import com.example.pos.BarangRepository
import com.example.pos.Model.Keranjang
import com.example.pos.Repo.KeranjangRepository

public class KasirActivityViewModel @ViewModelInject constructor(private val barangRepository: BarangRepository, private val keranjangRepository: KeranjangRepository) : BaseViewModel() {

    private var mTriggerFetchdata = MutableLiveData<Boolean>()
    private var barang : LiveData<List<Barang>> = Transformations.switchMap(mTriggerFetchdata){
        barangRepository.getAllData()
    }

    private var selectedProduct = MutableLiveData<List<Barang>>()

    init {
        selectedProduct.postValue(mutableListOf())
    }

    fun getAllbarang(): LiveData<List<Barang>> {
        return barang
    }

    fun loadbarang() {
        mTriggerFetchdata.value = true
    }

    fun transaksi(keranjang: Keranjang) {
        keranjangRepository.insertKeranjang(keranjang)
    }

    fun addSelectedProduct(barang: Barang){
        val tempSelectedProducts : MutableList<Barang> = if (selectedProduct.value == null){
            mutableListOf()
        }else{
            selectedProduct.value as MutableList<Barang>
        }
        val sameProduct : Barang? = tempSelectedProducts.find { p ->
            p.idbarang == barang.idbarang
        }
        sameProduct?.let {
            it.selectedQuantity = it.selectedQuantity?.plus(1)
        } ?: kotlin.run {
            tempSelectedProducts.add(barang)
        }
        selectedProduct.postValue(tempSelectedProducts)
    }

    fun decrementQuantityProduct(barang: Barang){
        val tempSelectedProducts : MutableList<Barang> = if (selectedProduct.value == null){
            mutableListOf()
        }else{
            selectedProduct.value as MutableList<Barang>

        }
        val sameProduct : Barang? = tempSelectedProducts.find { p ->
            p.idbarang == barang.idbarang
        }
        sameProduct?.let {
            if(it.selectedQuantity?.minus(1) == 0){
                tempSelectedProducts.remove(it)
            }else{
                it.selectedQuantity = it.selectedQuantity!!.minus(1)
            }
        }
        selectedProduct.postValue(tempSelectedProducts)
    }

    fun incrementQuantityProduct(barang: Barang){
        val tempSelectedProducts : MutableList<Barang> = if (selectedProduct.value == null){
            mutableListOf()
        }else{
            selectedProduct.value as MutableList<Barang>
        }
        val sameProduct : Barang? = tempSelectedProducts.find { p ->
            p.idbarang == barang.idbarang
        }
        sameProduct?.let {
            it.selectedQuantity = it.selectedQuantity!!.plus(1)
        }
        selectedProduct.postValue(tempSelectedProducts)
    }

//    fun listenToProducts() =
    fun listenToSelectedProduct() = selectedProduct




}