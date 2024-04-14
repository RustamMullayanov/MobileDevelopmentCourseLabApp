package com.example.mobiledevelopmentcourselabapp.presentation.view.list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.ItemAdCardBinding
import com.example.mobiledevelopmentcourselabapp.databinding.ItemPlayerBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.AdUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.ItemUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.PlayerUiModel

class PlayersAdapter(
    private val onPlayerClicked: (PlayerUiModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<ItemUiModel> = arrayListOf()

    fun updateItems(newItems: List<ItemUiModel>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("onCreateViewHolder", "Я создаль")
        return if (viewType == PLAYER_ID) {
            val binding =
                ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AvatarHolder(binding)
        } else {
            val binding =
                ItemAdCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AdHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is PlayerUiModel -> PLAYER_ID
            AdUiModel -> AD_ID
            else -> AD_ID
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (item is PlayerUiModel && holder is AvatarHolder) {
            holder.bind(item)

            holder.setOnClickListener {
                onPlayerClicked.invoke(item)
                item.isExpanded = !item.isExpanded
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class AvatarHolder(private val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(player: PlayerUiModel) {
            binding.name.text = player.name
            binding.number.text = player.number.toString()

            Glide
                .with(itemView)
                .load(player.photoUrl)
                .circleCrop()
                .placeholder(R.drawable.account)
                .into(binding.icon)
        }

        fun setOnClickListener(action: () -> Unit) {
            binding.root.setOnClickListener { action.invoke() }
        }
    }

    class AdHolder(binding: ItemAdCardBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        const val PLAYER_ID = 0
        const val AD_ID = 1
    }
}