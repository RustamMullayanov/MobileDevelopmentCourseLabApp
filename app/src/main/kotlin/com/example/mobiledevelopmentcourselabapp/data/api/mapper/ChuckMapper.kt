package com.example.mobiledevelopmentcourselabapp.data.api.mapper

import com.example.mobiledevelopmentcourselabapp.data.api.model.ChuckJokeResponse
import com.example.mobiledevelopmentcourselabapp.domain.model.ChuckJokeEntity
import javax.inject.Inject

class ChuckMapper @Inject constructor() {
    fun mapResponse( response: ChuckJokeResponse): ChuckJokeEntity {
        return ChuckJokeEntity(
            iconUrl = response.iconUrl,
            id = response.id,
            url = response.url,
            value = response.value
        )
    }
}