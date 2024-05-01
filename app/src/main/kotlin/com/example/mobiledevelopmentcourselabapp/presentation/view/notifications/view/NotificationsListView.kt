package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.view

import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseMvpView
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.model.NotificationModel
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface NotificationsListView : BaseMvpView {
    fun setList(list: List<NotificationModel>)
}