package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.presenter

import android.os.Bundle
import com.example.mobiledevelopmentcourselabapp.core.presentation.BasePresenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.model.NotificationDbModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.model.NotificationModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.presenter.NotificationAddPresenter.Companion.NOTIFICATION_RESULT
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.view.NotificationsListView
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

class NotificationsListPresenter @Inject constructor(): BasePresenter<NotificationsListView>() {
    private val notifications: MutableList<NotificationModel> = mutableListOf()
    private val database = Firebase.database
    private var ref: DatabaseReference? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        //viewState.showLogin()

        val user = Firebase.auth.currentUser

        val credential = EmailAuthProvider
            .getCredential("dmatafonov2000@mail.ru", "usue123")

        user?.reauthenticate(credential)
            ?.addOnCompleteListener { onAuth() }
    }

    fun onResult(bundle: Bundle) {
        val notification = bundle.getSerializable(NOTIFICATION_RESULT) as NotificationModel
        ref?.push()?.setValue(NotificationDbModel(notification.note, notification.dateTime.toString()))
    }

    fun onAuth() {
        ref = database.getReference("${Firebase.auth.currentUser?.uid}/notes")

        ref?.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                viewState.addNotification(mapSnapshotToNotification(snapshot))
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(snapshot: DataSnapshot) {}

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(error: DatabaseError) {}

        })

        ref?.get()?.addOnSuccessListener {
            notifications.addAll(it.children.map(::mapSnapshotToNotification))
            viewState.setList(notifications)
        }
    }

    private fun mapSnapshotToNotification(it: DataSnapshot): NotificationModel {
        val value = it.getValue<NotificationDbModel>()
        return NotificationModel(
            value?.note.orEmpty(),
            LocalDateTime.parse(value?.dateTime)
        )
    }
}