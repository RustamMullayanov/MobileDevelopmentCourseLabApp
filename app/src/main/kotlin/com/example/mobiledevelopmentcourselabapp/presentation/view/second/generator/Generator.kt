package com.example.mobiledevelopmentcourselabapp.presentation.view.second.generator


import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.AdUIModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.Board
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.ItemUIModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.PlayerUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.Position
import com.github.javafaker.Faker
import kotlin.random.Random


object Generator {
    private const val PLAYERS_COUNT = 100
    private const val PHOTO_LINK = "https://img.a.transfermarkt.technology/portrait/medium/"
    private val photos = arrayOf(
        "290532-1686212081.jpg", "709726-1672304545.jpg", "315252-1605116025.png", "748319-1694617058.jpg",
        "1036407-1706528684.jpg", "705864-1678301241.jpg", "149577-1617369576.png"
    )

    private const val PHOTO_LINK_BOARD = "https://via.placeholder.com/"
    private val photosBoard = arrayOf(
        "200x50", "205x55", "210x60"
    )
    private val textArray = arrayOf(
        "ВВВВВВ", "МВМВААА", "СВЫСВЫСЫВ", "ЩСЩЩСЩСЩС"
    )

    fun generate(): List<ItemUIModel> {
        val faker = Faker()
        return mutableListOf<ItemUIModel>().apply {
            repeat(PLAYERS_COUNT) {
                add(
                    PlayerUiModel(
                        name = faker.name().fullName(),
                        team = faker.team().name(),
                        number = (1..25).random(),
                        age = (18..35).random(),
                        position = Position.values().random(),
                        photoUrl = PHOTO_LINK + photos.random()

                    )
                )
                if(Random.nextBoolean()) add(AdUIModel(faker.funnyName().name()))

                if(Random.nextBoolean()) add(Board(PHOTO_LINK_BOARD + photosBoard.random(),
                    textArray.random()))

            }
        }
    }

}