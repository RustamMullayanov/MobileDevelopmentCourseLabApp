package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.presenter

import android.os.Bundle
import com.example.mobiledevelopmentcourselabapp.core.presentation.BasePresenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.model.NotificationModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.presenter.NotificationAddPresenter.Companion.NOTIFICATION_RESULT
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.view.NotificationsListView
import javax.inject.Inject

class NotificationsListPresenter @Inject constructor(): BasePresenter<NotificationsListView>() {
    private val notifications: MutableList<NotificationModel> = mutableListOf()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setList(notifications)
    }

    fun onResult(bundle: Bundle) {
        val notification = bundle.getSerializable(NOTIFICATION_RESULT) as NotificationModel
        notifications.add(notification)
        viewState.setList(notifications)
    }
}