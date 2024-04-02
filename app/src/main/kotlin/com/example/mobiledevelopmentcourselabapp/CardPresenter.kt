package com.example.mobiledevelopmentcourselabapp

import android.content.Intent
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.CardFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.PlayerUiModel
import moxy.MvpPresenter
import javax.inject.Inject

class CardPresenter @Inject constructor(): MvpPresenter<CardMvpView>(){


    fun onShareButton(playerUiModel: PlayerUiModel){
        val playerState = "Имя: "+ playerUiModel.name + "\n" + "Возраст: "+ playerUiModel.age + "\n" + "Позиция: "+playerUiModel.position + "\n" + "Команда: "+playerUiModel.team + "\n" + "Номер игрока: "+playerUiModel.number
        viewState.sharePlayer(playerState)
    }
}