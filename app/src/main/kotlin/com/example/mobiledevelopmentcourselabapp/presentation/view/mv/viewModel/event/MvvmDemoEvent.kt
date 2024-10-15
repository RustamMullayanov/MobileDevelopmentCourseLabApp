package com.example.mobiledevelopmentcourselabapp.presentation.view.mv.viewModel.event

sealed interface DemoEvent {
    object HeightDoneClicked: DemoEvent
    class HeightChanged(val h: CharSequence?): DemoEvent
    object WeightDoneClicked: DemoEvent
    class WeightChanged(val w: CharSequence?): DemoEvent
    object RepeatClicked: DemoEvent
}