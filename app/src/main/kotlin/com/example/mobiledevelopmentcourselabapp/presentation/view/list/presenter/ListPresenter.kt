package com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter

import com.example.mobiledevelopmentcourselabapp.presentation.view.list.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.PlayerUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.view.ListMvpView
import moxy.MvpPresenter
import javax.inject.Inject

class ListPresenter @Inject constructor(): MvpPresenter<ListMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val players = Generator.generate()
        viewState.showPlayers(players)
    }

    fun onPlayerClicked(playerUiModel: PlayerUiModel) {
        viewState.navigateToPlayer(playerUiModel)
    }

}