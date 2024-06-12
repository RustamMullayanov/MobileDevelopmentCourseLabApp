package com.example.mobiledevelopmentcourselabapp.presentation.view.second
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.NeuroUIClass
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.ItemUIModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface ListMvpView: MvpView {
    fun showNeuros(neuros: List<ItemUIModel>)

    @OneExecution
    fun navigateToNeuro(neuro: NeuroUIClass)
}