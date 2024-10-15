package com.example.mobiledevelopmentcourselabapp.presentation.view.mv.viewModel

import androidx.lifecycle.ViewModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.mv.presenter.LogicModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.mv.viewModel.event.DemoEvent
import com.example.mobiledevelopmentcourselabapp.presentation.view.mv.viewModel.state.MvvmDemoState
import com.example.mobiledevelopmentcourselabapp.presentation.view.mv.viewModel.state.ScreenMode
import com.example.mobiledevelopmentcourselabapp.utils.orFalse
import com.example.mobiledevelopmentcourselabapp.utils.orZero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MvvmDemoViewModel: ViewModel() {
    private val mutableState = MutableStateFlow(MvvmDemoState())
    val viewState = mutableState as StateFlow<MvvmDemoState>

    fun sendEvent(event: DemoEvent) {
        when (event) {
            DemoEvent.HeightDoneClicked -> onHeightDoneClick()
            is DemoEvent.HeightChanged -> onHeightChanged(event.h)
            DemoEvent.WeightDoneClicked -> onWeightDoneClick()
            is DemoEvent.WeightChanged -> onWeightChanged(event.w)
            DemoEvent.RepeatClicked -> onRepeatClick()
        }
    }

    private fun onHeightChanged(text: CharSequence?) {
        mutableState.update {
            it.copy(
                height = text.toString().toIntOrNull().orZero(),
                heightDoneEnabled = text?.isNotEmpty().orFalse()
            )
        }
    }

    private fun onHeightDoneClick() {
        mutableState.update {
            it.copy(mode = ScreenMode.WEIGHT)
        }
    }

    private fun onWeightChanged(text: CharSequence?) {
        mutableState.update {
            it.copy(
                weight = text.toString().toIntOrNull().orZero(),
                weightDoneEnabled = text?.isNotEmpty().orFalse()
            )
        }
    }

    private fun onWeightDoneClick() {
        mutableState.update {
            it.copy(
                mode = ScreenMode.RESULT,
                showDialog = true,
                result = LogicModel.calculate(viewState.value.height, viewState.value.weight)
            )
        }
        mutableState.update { it.copy(showDialog = false) }
    }

    private fun onRepeatClick() {
        mutableState.update {
            it.copy(mode = ScreenMode.HEIGHT)
        }
    }
}