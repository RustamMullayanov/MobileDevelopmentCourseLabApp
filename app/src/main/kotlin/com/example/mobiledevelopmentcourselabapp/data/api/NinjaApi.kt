package com.example.mobiledevelopmentcourselabapp.data.api

import com.example.mobiledevelopmentcourselabapp.data.model.NinjaJokeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface NinjaApi {
    @GET("chucknorris")
    fun getRandomJoke(): Single<NinjaJokeResponse>
}