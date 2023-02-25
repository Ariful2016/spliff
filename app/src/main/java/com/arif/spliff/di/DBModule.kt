package com.arif.spliff.di

import android.content.Context
import androidx.room.Room
import com.arif.spliff.dao.CartDao
import com.arif.spliff.dao.ProductDao
import com.arif.spliff.room.ProductDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DBModule {


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ProductDB {
        return Room.databaseBuilder(
            appContext,
            ProductDB::class.java,
            "ProductDB"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideProductDao(appDatabase: ProductDB): ProductDao {
        return appDatabase.getProductDao()
    }

    @Provides
    fun provideCartDao(appDatabase: ProductDB): CartDao {
        return appDatabase.getCartDao()
    }


}