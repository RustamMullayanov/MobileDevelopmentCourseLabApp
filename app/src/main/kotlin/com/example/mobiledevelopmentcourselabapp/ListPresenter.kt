package com.example.mobiledevelopmentcourselabapp

import com.example.mobiledevelopmentcourselabapp.presentation.view.second.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.PlayerUiModel
import moxy.MvpPresenter
import javax.inject.Inject

class ListPresenter @Inject constructor(): MvpPresenter<ListMvpView>() {

    fun onPlayerClicked(playerUiModel: PlayerUiModel){
        viewState.navigateToPlayer(playerUiModel)
    }

    override fun onFirstViewAttach() {

        super.onFirstViewAttach()
        viewState.showPlayers(Generator.generate())
    }
}