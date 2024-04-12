package com.example.mobiledevelopmentcourselabapp.presentation.view.third

import com.example.mobiledevelopmentcourselabapp.domain.model.ChuckJokeEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface ThirdView: MvpView {
    @AddToEndSingle
    fun setJoke(jokeEntity: ChuckJokeEntity)
    @OneExecution
    fun navigateToSite(jokeEntity: ChuckJokeEntity)
}