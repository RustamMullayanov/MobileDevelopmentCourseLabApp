package com.example.mobiledevelopmentcourselabapp.data.repository

import com.example.mobiledevelopmentcourselabapp.data.api.ChuckApi
import com.example.mobiledevelopmentcourselabapp.data.api.mapper.ChuckMapper
import com.example.mobiledevelopmentcourselabapp.domain.model.ChuckJokeEntity
import com.example.mobiledevelopmentcourselabapp.domain.repository.IChuckRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ChuckRepository @Inject constructor (
    private  val chuckApi : ChuckApi,
    private val chuckMapper :ChuckMapper) : IChuckRepository {
    override fun getRandomJoke(): Single<ChuckJokeEntity> =
        chuckApi.getRandomJoke().map {chuckMapper.mapResponse((it))}
    }

