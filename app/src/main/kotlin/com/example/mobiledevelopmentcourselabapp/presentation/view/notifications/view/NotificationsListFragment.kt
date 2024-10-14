package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseFragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentNotificationsListBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.adapter.NotificationsAdapter
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.model.NotificationModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.presenter.NotificationsListPresenter
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


class NotificationsListFragment: BaseFragment(), NotificationsListView {

    private var binding: FragmentNotificationsListBinding? = null

    private val adapter by lazy { NotificationsAdapter() }

    @Inject
    lateinit var presenterProvider: Provider<NotificationsListPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    private val signInLauncher : ActivityResultLauncher<Intent> =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { presenter.onAuth() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        FragmentNotificationsListBinding.inflate(inflater, container, false).apply {
            binding = this
            return this.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.notificationsList?.adapter = adapter
        binding?.addButton?.setOnClickListener { findNavController().navigate(R.id.action_navigation_notifications_to_add) }

        setFragmentResultListener(DEFAULT_RESULT_KEY) { _, bundle ->
            presenter.onResult(bundle)
        }
    }

    override fun setList(list: List<NotificationModel>) {
        adapter.setNotifications(list)
    }

    override fun showLogin() {
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .build()
        signInLauncher.launch(signInIntent)
    }

    override fun addNotification(model: NotificationModel) {
        adapter.addNotification(model)
    }
}