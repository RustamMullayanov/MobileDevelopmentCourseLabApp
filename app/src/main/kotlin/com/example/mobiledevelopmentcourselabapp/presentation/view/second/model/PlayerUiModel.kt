package com.example.mobiledevelopmentcourselabapp.presentation.view.second.model

import java.io.Serializable
import java.time.LocalTime
interface ItemUIModel : Serializable

data class PlayerUiModel(
    val name: String,
    val photoUrl: String,
    val number: Int,
    val team: String,
    val position: Position,
    val age: Int,
    var isExpanded: Boolean = false,
    val gameCount: Int,
    val goalCount: Int,
    val redCardCount: Int,
    val yellowCardCount: Int,
    val assistCount: Int


): ItemUIModel


data class Board(
    val photoUrlBoard: String,
    val textBoard: String
):ItemUIModel


enum class Position(val rusName: String){
    GOALKEEPER("Вратарь"),
    DEFENDER("Защитник"),
    MIDFIELD("Полузащитник"),
    FORWARD("Нападающий")
}

class AdUIModel(val text: String): ItemUIModel
