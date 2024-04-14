package com.example.mobiledevelopmentcourselabapp.domain.interactor

import com.example.mobiledevelopmentcourselabapp.data.repository.PlayerRepository
import com.example.mobiledevelopmentcourselabapp.domain.model.PlayerPosition
import javax.inject.Inject

class PlayerInteractor @Inject constructor(
    private val repository: PlayerRepository
) {
    fun getAll() = repository.getAll()

    fun addPlayer(name: String, number: Int, position: PlayerPosition) =
        repository.addPlayer(name, number, position)
}