package com.example.mobiledevelopmentcourselabapp.domain.model

enum class PlayerPosition(val rusName: String = "") {
    GOALKEEPER("Вратарь"),
    DEFENDER("Защитник"),
    MIDFIELD("Полузащитник"),
    FORWARD("Нападающий"),
    NONE;

    companion object {
        fun getByName(name: String) = values().find { it.name == name } ?: NONE
    }
}