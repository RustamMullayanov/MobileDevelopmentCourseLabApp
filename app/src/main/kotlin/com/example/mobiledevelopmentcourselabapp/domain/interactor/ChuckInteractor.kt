package com.example.mobiledevelopmentcourselabapp.domain.interactor

import com.example.mobiledevelopmentcourselabapp.domain.repository.IChuckRepository
import javax.inject.Inject

class ChuckInteractor @Inject constructor(
    private val repository: IChuckRepository
) {
    fun getRandomJoke() = repository.getRandomJoke()

    fun getJokeByCategory(category: String) = repository.getJokeByCategory(category)

    fun getCategories() = repository.getCategories()

    fun getJokeFromNinja() = repository.getJokeFromNinja()
}