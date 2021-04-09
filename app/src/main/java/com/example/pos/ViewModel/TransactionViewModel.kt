package com.example.pos.ViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.pos.Barang
import com.example.pos.BarangRepository
import com.example.pos.Model.Keranjang
import com.example.pos.Repo.KeranjangRepository


class TransactionViewModel @ViewModelInject constructor(private val keranjangRepository: KeranjangRepository, private val barangRepository: BarangRepository) : BaseViewModel() {

     private var mTrigger = MutableLiveData<String>()
    private var mTriggerKeranjang = MutableLiveData<Long>()
     private var barang: LiveData<Barang> = Transformations.switchMap(mTrigger) {
         barangRepository.getDataById(it)
     }
    private var keranjang: LiveData<Keranjang> = Transformations.switchMap(mTriggerKeranjang) {
        keranjangRepository.getDataById(it)
    }

    private var selectedProduct = MutableLiveData<List<Barang>>()

    init {
        selectedProduct.postValue(mutableListOf())
    }

     fun insertKeranjang(keranjang: Keranjang) {
         keranjangRepository.insertKeranjang(keranjang)

     }

     fun getBarangById() = barang

     fun triggerFetchdata(idbarang: String) {
         mTrigger.value = idbarang
     }

    fun triggerFetchKeranjang(idkeranjang: Long) {
        mTriggerKeranjang.value = idkeranjang
    }

    fun getKeranjangById() = keranjang

    fun getAllKeranjang(): LiveData<Keranjang> {
        return keranjang
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

