package com.arif.spliff.di

import com.arif.spliff.network.AuthApi
import com.arif.spliff.network.ProductApi
import com.arif.spliff.utils.AUTH_BASE_URL
import com.arif.spliff.utils.PRODUCT_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetWorkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }


    @Provides
    @Singleton
    fun providesSecuredApi(
        retrofitBuilder: Retrofit.Builder
    ): AuthApi {
        return retrofitBuilder.build().create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun providesProductApi(
    ): ProductApi {
        val retrofit: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(PRODUCT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        return retrofit.build().create(ProductApi::class.java)
    }


}