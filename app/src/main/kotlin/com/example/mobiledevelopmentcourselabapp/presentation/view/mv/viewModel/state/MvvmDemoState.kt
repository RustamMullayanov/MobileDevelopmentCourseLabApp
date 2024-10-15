package com.example.mobiledevelopmentcourselabapp.presentation.view.mv.viewModel.state

data class MvvmDemoState(
    val mode: ScreenMode = ScreenMode.HEIGHT,
    val height: Int = 0,
    val heightDoneEnabled: Boolean = false,
    val weight: Int = 0,
    val weightDoneEnabled: Boolean = false,
    val result: String = "",
    val showDialog: Boolean = false
) {
    val heightVisibility: Boolean get() = mode == ScreenMode.HEIGHT
    val weightVisibility: Boolean get() = mode == ScreenMode.WEIGHT
    val resultVisibility: Boolean get() = mode == ScreenMode.RESULT
}


enum class ScreenMode {
    HEIGHT,
    WEIGHT,
    RESULT
}