package com.example.mobiledevelopmentcourselabapp.presentation.view.list.view

import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseMvpView
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.ItemUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.PlayerUiModel
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface ListMvpView: BaseMvpView {
    fun showPlayers(players: List<ItemUiModel>)
    @OneExecution
    fun navigateToPlayer(player: PlayerUiModel)
}