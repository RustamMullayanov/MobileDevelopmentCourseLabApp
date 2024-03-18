package com.example.mobiledevelopmentcourselabapp.data.provider

import com.example.mobiledevelopmentcourselabapp.data.api.NinjaApi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NinjaApiProvider @Inject constructor() {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        .addInterceptor { chain ->
            val request: Request = chain.request()

            val newRequest: Request = request.newBuilder()
                .addHeader("X-Api-Key", "ПОСТАВЬ СЮДА СВОЙ")
                .build()
            chain.proceed(newRequest)
        }
        .build()

    val retrofitNinjaApi: NinjaApi = Retrofit.Builder()
        .baseUrl("https://api.api-ninjas.com/v1/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
        .create(NinjaApi::class.java)
}