package com.example.mobiledevelopmentcourselabapp.presentation.view.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.ItemPlayerBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.PlayerUiModel

class PlayersAdapter : RecyclerView.Adapter<PlayersAdapter.AvatarHolder>() {

    private var items: MutableList<PlayerUiModel> = arrayListOf()

    fun updateItems(newItems: List<PlayerUiModel>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player, parent, false)

        return AvatarHolder(view)
    }

    override fun onBindViewHolder(holder: AvatarHolder, position: Int) {
        val player = items[position]

        with(holder.binding) {
            name.text = player.name
            number.text = player.number.toString()

            additionalFields.isVisible = player.isExpanded

            age.text = holder.itemView.context.resources.getString(
                R.string.age_pattern,
                player.age,
                holder.itemView.context.resources.getQuantityText(R.plurals.age, player.age)
            ) // первый подход

            playerPosition.text = player.formattedPosition
            team.text = player.formattedTeam // второй подход

            Glide
                .with(holder.itemView)
                .load(player.photoUrl)
                .into(icon)

            root.setOnClickListener {
                player.isExpanded = !player.isExpanded
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class AvatarHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemPlayerBinding.bind(itemView)
    }
}