package com.example.pos.Repo

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.pos.Barang
import com.example.pos.DAO.KeranjangDao
import com.example.pos.Model.Keranjang
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class KeranjangRepository @Inject constructor(
        keranjangDao: KeranjangDao,
        compositeDisposable: CompositeDisposable
) {
    var krDao : KeranjangDao = keranjangDao
    var comp : CompositeDisposable = compositeDisposable

    fun insertKeranjang(keranjang: Keranjang) {
        comp.add(io.reactivex.Observable.fromCallable { krDao.tambahKeranjang(keranjang) }
                .subscribeOn(Schedulers.computation())
                .subscribe())
    }

    fun getAllData() : LiveData<List<Keranjang>> {
        return LiveDataReactiveStreams.fromPublisher(krDao.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation()))
    }


    fun getDataById(id: Long): LiveData<Keranjang>{
        return LiveDataReactiveStreams.fromPublisher(krDao.getById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation()))
    }
}