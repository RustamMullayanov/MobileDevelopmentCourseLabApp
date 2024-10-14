package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.view

import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseMvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface NotificationAddView: BaseMvpView {
    fun showDatePicker()
    fun showTimePicker()
    fun setDate(date: String)
    fun showTime(time: String)
}