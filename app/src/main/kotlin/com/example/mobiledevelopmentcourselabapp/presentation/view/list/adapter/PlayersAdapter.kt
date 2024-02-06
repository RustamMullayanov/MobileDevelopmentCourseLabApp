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

        holder.bind(player)

        holder.setOnClickListener {
            player.isExpanded = !player.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = items.size

    class AvatarHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPlayerBinding.bind(itemView)

        fun bind(player: PlayerUiModel) {
            binding.name.text = player.name
            binding.number.text = player.number.toString()

            binding.additionalFields.isVisible = player.isExpanded

            binding.age.text = itemView.context.resources.getString(
                R.string.age_pattern,
                player.age,
                itemView.context.resources.getQuantityText(R.plurals.age, player.age)
            ) // первый подход

            binding.playerPosition.text = player.formattedPosition
            binding.team.text = player.formattedTeam // второй подход

            Glide
                .with(itemView)
                .load(player.photoUrl)
                .into(binding.icon)
        }

        fun setOnClickListener(action: () -> Unit) {
            binding.root.setOnClickListener { action.invoke() }
        }
    }
}