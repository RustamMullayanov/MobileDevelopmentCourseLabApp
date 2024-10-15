package com.example.mobiledevelopmentcourselabapp.presentation.view.mv.fragment.view

import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseMvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface MvpDemoView: BaseMvpView {
    fun setHeightVisibility(isVisible: Boolean)
    fun setHeightDoneEnabled(isEnabled: Boolean)
    fun setWeightVisibility(isVisible: Boolean)
    fun setWeightDoneEnabled(isEnabled: Boolean)
    fun setResultVisibility(isVisible: Boolean)
    @OneExecution
    fun setResult(result: String)
}