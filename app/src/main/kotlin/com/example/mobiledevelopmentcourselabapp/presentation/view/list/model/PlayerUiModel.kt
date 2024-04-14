package com.example.mobiledevelopmentcourselabapp.presentation.view.list.model

import com.example.mobiledevelopmentcourselabapp.domain.model.PlayerPosition
import java.io.Serializable

interface ItemUiModel: Serializable

data class PlayerUiModel(
    val name: String,
    val photoUrl: String = "",
    val number: Int,
    val team: String  = "",
    val position: PlayerPosition,
    val age: Int = 0,
    val gamesCount: Int = 0,
    val goalsCount: Int = 0,
    val assistsCount: Int = 0,
    val yellowCardCount: Int = 0,
    val redCardsCount: Int = 0,
    var isExpanded: Boolean = false
): ItemUiModel

object AdUiModel : ItemUiModel