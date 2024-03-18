package com.example.mobiledevelopmentcourselabapp.data.repository

import com.example.mobiledevelopmentcourselabapp.data.api.ChuckApi
import com.example.mobiledevelopmentcourselabapp.data.api.NinjaApi
import com.example.mobiledevelopmentcourselabapp.data.mapper.ChuckMapper
import com.example.mobiledevelopmentcourselabapp.domain.model.ChuckJokeEntity
import com.example.mobiledevelopmentcourselabapp.domain.repository.IChuckRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ChuckRepository @Inject constructor(
    private val api: ChuckApi,
    private val ninjaApi: NinjaApi,
    private val mapper: ChuckMapper
): IChuckRepository {
    override fun getRandomJoke(): Single<ChuckJokeEntity> =
        api.getRandomJoke().map { mapper.mapResponse(it) }

    override fun getJokeByCategory(category: String): Single<ChuckJokeEntity> =
        api.getJokeByCategory(category).map { mapper.mapResponse(it) }

    override fun getCategories(): Single<List<String>> =
        api.getCategories()

    override fun getJokeFromNinja(): Single<String> =
        ninjaApi.getRandomJoke().map { it.joke }
}