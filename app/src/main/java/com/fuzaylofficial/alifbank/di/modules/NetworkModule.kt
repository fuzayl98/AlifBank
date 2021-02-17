package com.fuzaylofficial.alifbank.di.modules

import com.fuzaylofficial.alifbank.Constants
import com.fuzaylofficial.alifbank.ui.main.repository.remote.GuidesService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule{

    @Provides
    fun bindService(retrofit: Retrofit = getRetrofit()): GuidesService {
        return retrofit.create(GuidesService::class.java)
    }

    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getOkHttpNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest =
                chain.request().newBuilder()/*.header(Можно добавить клячи через Interceptor)*/.build()
            chain.proceed(newRequest)
        }
    }

    fun getOkHttpClient(okHttpNetworkInterceptor: Interceptor = getOkHttpNetworkInterceptor()): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(okHttpNetworkInterceptor)
            .build()
    }

}