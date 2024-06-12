package com.example.mobiledevelopmentcourselabapp.presentation.view.presenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.NeuroUIClass
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.ListMvpView
import moxy.MvpPresenter
import javax.inject.Inject

class ListPresenter @Inject constructor(): MvpPresenter<ListMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val players = Generator.generate()
        viewState.showNeuros(players)
    }

    fun onNeuroClicked(playerUiModel: NeuroUIClass) {
        viewState.navigateToNeuro(playerUiModel)
    }

}