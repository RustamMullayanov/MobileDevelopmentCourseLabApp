package com.example.mobiledevelopmentcourselabapp.presentation.view.mv.presenter


import com.example.mobiledevelopmentcourselabapp.core.presentation.BasePresenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.mv.fragment.view.MvpDemoView
import com.example.mobiledevelopmentcourselabapp.utils.orFalse
import com.example.mobiledevelopmentcourselabapp.utils.orZero
import kotlin.properties.Delegates

class MvpDemoPresenter: BasePresenter<MvpDemoView>() {
    private var h = 0
    private var w = 0

    private var mode: ScreenMode by Delegates.observable(ScreenMode.HEIGHT) { _, _, new ->
        onModeChanged(new)
    }

    override fun onFirstViewAttach() {
        viewState.setHeightDoneEnabled(false)
        viewState.setWeightDoneEnabled(false)
    }

    enum class ScreenMode {
        HEIGHT,
        WEIGHT,
        RESULT
    }

    private fun onModeChanged(mode: ScreenMode) {
        viewState.setHeightVisibility(mode == ScreenMode.HEIGHT)
        viewState.setWeightVisibility(mode == ScreenMode.WEIGHT)
        viewState.setResultVisibility(mode == ScreenMode.RESULT)
    }

    fun onHeightChanged(text: CharSequence?) {
        h = text.toString().toIntOrNull().orZero()
        viewState.setHeightDoneEnabled(text?.isNotEmpty().orFalse())
    }

    fun onWeightChanged(text: CharSequence?) {
        w = text.toString().toIntOrNull().orZero()
        viewState.setWeightDoneEnabled(text?.isNotEmpty().orFalse())
    }

    fun onHeightDoneClick() {
        mode = ScreenMode.WEIGHT
    }

    fun onWeightDoneClick() {
        mode = ScreenMode.RESULT
        viewState.setResult(LogicModel.calculate(h, w))
    }

    fun onRepeatClick() {
        mode = ScreenMode.HEIGHT
    }
}