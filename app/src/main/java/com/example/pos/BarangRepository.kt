package com.example.pos

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.pos.DAO.BarangDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class BarangRepository @Inject constructor(
        barangDao: BarangDao,
        compositeDisposable: CompositeDisposable
) {
    var brDao : BarangDao = barangDao
    var comp : CompositeDisposable = compositeDisposable

    fun getAllData() : LiveData<List<Barang>>{
        return LiveDataReactiveStreams.fromPublisher(brDao.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation()))
    }

    fun insertbarang(barang: Barang) {
        comp.add(Observable.fromCallable { brDao.tambahBarang(barang) }
            .subscribeOn(Schedulers.computation())
            .subscribe())
    }

    fun getDataById(id: String): LiveData<Barang>{
        return LiveDataReactiveStreams.fromPublisher(brDao.getById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation()))
    }

    fun deleteBarang(barang: Barang) {
        comp.add(Observable.fromCallable { brDao.deleteBarang(barang) }
            .subscribeOn(Schedulers.computation())
            .subscribe())
    }

    fun updateBarang(idbarang: Long, namabarang: String, hargabarang: String, stockbarang: String) {
        comp.add(Observable.fromCallable { brDao.updateBarang(idbarang, namabarang, hargabarang, stockbarang) }
            .subscribeOn(Schedulers.computation())
            .subscribe())
    }
}

