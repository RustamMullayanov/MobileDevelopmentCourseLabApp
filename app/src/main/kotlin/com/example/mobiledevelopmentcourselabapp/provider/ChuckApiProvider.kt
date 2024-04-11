package com.example.mobiledevelopmentcourselabapp.provider

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mobiledevelopmentcourselabapp.data.api.ChuckApi
import com.google.gson.Gson
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import javax.inject.Inject

class ChuckApiProvider @Inject constructor() {
    @RequiresApi(Build.VERSION_CODES.O)
    private var httpClient = OkHttpClient.Builder()
        .readTimeout(Duration.ofSeconds(3))
        .connectTimeout(Duration.ofMillis(500))
        .addInterceptor(HttpLoggingInterceptor().apply {setLevel(HttpLoggingInterceptor.Level.BODY)})
        .build()
    @RequiresApi(Build.VERSION_CODES.O)
    val chuckApi: ChuckApi = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(ChuckApi::class.java)
}