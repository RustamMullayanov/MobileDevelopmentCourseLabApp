package com.example.mobiledevelopmentcourselabapp.presentation.view.list.model

interface ItemUiModel

class PlayerUiModel(
    val name: String,
    val photoUrl: String,
    val number: Int,
    val team: String,
    val position: Position,
    val age: Int,
    var isExpanded: Boolean = false
): ItemUiModel {
    val formattedTeam = "Команда: $team"
    val formattedPosition = "Позиция: ${position.rusName}"
}

enum class Position(val rusName: String = "") {
    GOALKEEPER("Вратарь"),
    DEFENDER("Защитник"),
    MIDFIELD("Полузащитник"),
    FORWARD("Нападающий")
}

object AdUiModel : ItemUiModel