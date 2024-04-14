package com.example.mobiledevelopmentcourselabapp.presentation.view.list.generator

import com.example.mobiledevelopmentcourselabapp.domain.model.PlayerPosition
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.ItemUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.PlayerUiModel
import com.github.javafaker.Faker

object Generator {
    private const val PLAYERS_COUNT = 100
    private const val PHOTO_LINK = "https://img.a.transfermarkt.technology/portrait/medium/"
    private val photos = arrayOf(
        "290532-1686212081.jpg", "709726-1672304545.jpg", "315252-1605116025.png", "748319-1694617058.jpg",
        "1036407-1706528684.jpg", "705864-1678301241.jpg", "149577-1617369576.png"
    )

    fun generate(): List<ItemUiModel> {
        val faker = Faker()
        return mutableListOf<ItemUiModel>().apply {
            repeat(PLAYERS_COUNT) {
                add(
                    PlayerUiModel(
                        name = faker.name().fullName(),
                        team = faker.team().name(),
                        number = (1..25).random(),
                        age = (18..35).random(),
                        position = PlayerPosition.values().random(),
                        photoUrl = PHOTO_LINK + photos.random(),
                        gamesCount = (1..50).random(),
                        assistsCount = (1..25).random(),
                        goalsCount = (1..25).random(),
                        redCardsCount = (1..5).random(),
                        yellowCardCount = (1..5).random()
                    )
                )
                //if (Random.nextBoolean()) add(AdUiModel)
            }
        }
    }
}