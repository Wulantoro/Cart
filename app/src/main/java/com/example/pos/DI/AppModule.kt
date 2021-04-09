package com.example.pos.DI

import android.content.Context
import androidx.room.Room
import com.example.pos.BarangDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.disposables.CompositeDisposable

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Provides
    @DbName
    fun provideDbName() = "kasir.db"

    @Provides
    fun provideBarangdatabase(@ApplicationContext context: Context, @DbName dbName: String): BarangDatabase {
        return Room.databaseBuilder(context,
        BarangDatabase::class.java, dbName)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    fun provideBarangdao(barangDatabase: BarangDatabase) = barangDatabase.getBarangDao()

    @Provides
    fun provideKeranjangDao(barangDatabase: BarangDatabase) = barangDatabase.getKeranjangDao()

    @Provides fun provideCompositeDisposable() = CompositeDisposable()
}