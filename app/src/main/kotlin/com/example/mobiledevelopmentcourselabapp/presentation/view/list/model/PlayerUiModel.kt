package com.example.mobiledevelopmentcourselabapp.presentation.view.list.model

import java.io.Serializable

interface ItemUiModel: Serializable

data class PlayerUiModel(
    val name: String,
    val photoUrl: String,
    val number: Int,
    val team: String,
    val position: Position,
    val age: Int,
    val gamesCount: Int,
    val goalsCount: Int,
    val assistsCount: Int,
    val yellowCardCount: Int,
    val redCardsCount: Int,
    var isExpanded: Boolean = false
): ItemUiModel

enum class Position(val rusName: String = "") {
    GOALKEEPER("Вратарь"),
    DEFENDER("Защитник"),
    MIDFIELD("Полузащитник"),
    FORWARD("Нападающий")
}

object AdUiModel : ItemUiModel