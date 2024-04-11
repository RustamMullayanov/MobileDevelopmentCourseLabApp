package com.example.mobiledevelopmentcourselabapp.data.api.mapper

import com.example.mobiledevelopmentcourselabapp.data.api.model.ChuckJokeResponse
import com.example.mobiledevelopmentcourselabapp.domain.model.ChuckJokeEntity

class ChuckMapper {
    fun mapResponse( response: ChuckJokeResponse): ChuckJokeEntity {
        return ChuckJokeEntity(
            iconUrl = response.iconUrl,
            id = response.id,
            url = response.url,
            value = response.value
        )
    }
}