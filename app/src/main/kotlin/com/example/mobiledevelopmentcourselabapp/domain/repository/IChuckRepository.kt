package com.example.mobiledevelopmentcourselabapp.domain.repository

import com.example.mobiledevelopmentcourselabapp.domain.model.ChuckJokeEntity
import io.reactivex.rxjava3.core.Single

interface IChuckRepository {
    fun getRandomJoke(): Single<ChuckJokeEntity>

    fun getJokeByCategory(category: String): Single<ChuckJokeEntity>

    fun getCategories(): Single<List<String>>

    fun getJokeFromNinja(): Single<String>
}