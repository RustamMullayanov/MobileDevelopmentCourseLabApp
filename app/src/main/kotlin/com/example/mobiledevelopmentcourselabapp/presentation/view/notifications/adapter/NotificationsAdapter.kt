package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopmentcourselabapp.databinding.ItemNotificationBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.model.NotificationModel

class NotificationsAdapter : RecyclerView.Adapter<NotificationsAdapter.NotificationHolder>() {

    private var notifications = mutableListOf<NotificationModel>()

    fun addNotification(note: NotificationModel) {
        notifications.add(note)
        notifyItemInserted(notifications.size - 1)
    }

    fun setNotifications(notes: List<NotificationModel>) {
        notifications = notes.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val binding =
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationHolder(binding)
    }

    override fun getItemCount() = notifications.size

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        val item = notifications[position]
        holder.bind(item)
    }

    inner class NotificationHolder(private val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notification: NotificationModel) {
            binding.note.text = notification.note
            binding.dateTime.text = notification.dateTime.toString()
        }
    }
}