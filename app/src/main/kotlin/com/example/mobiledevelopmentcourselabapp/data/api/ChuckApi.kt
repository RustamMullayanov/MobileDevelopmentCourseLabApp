package com.example.mobiledevelopmentcourselabapp.data.api

import com.example.mobiledevelopmentcourselabapp.data.api.model.ChuckJokeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
interface ChuckApi {
    @GET("jokes/random")
    fun getRandomJoke(): Single<ChuckJokeResponse>
}