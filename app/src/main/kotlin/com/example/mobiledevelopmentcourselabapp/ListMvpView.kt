package com.example.mobiledevelopmentcourselabapp

import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.ItemUIModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.PlayerUiModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface ListMvpView : MvpView {

    @OneExecution
    fun navigateToPlayer(playerUiModel: PlayerUiModel)

    @AddToEndSingle
    fun showPlayers(player: List<ItemUIModel>)
}