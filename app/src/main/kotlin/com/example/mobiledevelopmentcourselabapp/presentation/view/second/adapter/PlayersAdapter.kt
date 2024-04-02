package com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.ItemAdBinding
import com.example.mobiledevelopmentcourselabapp.databinding.ItemBoardBinding
import com.example.mobiledevelopmentcourselabapp.databinding.ItemPlayerBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.AdUIModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.Board
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.ItemUIModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.PlayerUiModel


class PlayersAdapter(private val onPlayerClicked : (PlayerUiModel)-> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //БД ПРО ИНФУ ОБ ИГРОКАХ
    private  var items: MutableList<ItemUIModel> = arrayListOf();

    fun updateItems(newItems: List<ItemUIModel>)
    {
        items = newItems.toMutableList()
    }

    //КОЛЛИЧЕСТВО ИГРОКОВ В СПИСКЕ
    override fun getItemCount(): Int {
        return  items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is PlayerUiModel -> PLAYER_ID
            is Board -> BOARD_ID
            else -> AD_ID

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == PLAYER_ID) {
            val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

            return AvatarHolder(binding)
        }
        if(viewType == BOARD_ID){
            val binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return BoardHolder(binding)
        }
        val binding = ItemAdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  AdHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if(holder is AvatarHolder && item is PlayerUiModel){
            holder.bind(item)
            holder.setOnClick {
                onPlayerClicked.invoke(item)
                item.isExpanded = !item.isExpanded
                notifyItemChanged(position)
            }
        }

        if(holder is AdHolder && item is AdUIModel){
            holder.bind(item)
        }
        if(holder is BoardHolder && item is Board){
            holder.bind(item)
        }
    }

    class AvatarHolder(private val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(player: PlayerUiModel){
            binding.name.text = player.name
            binding.number.text = player.number.toString()
            binding.additionalFields.isVisible = player.isExpanded

            binding.age.text = itemView.context.resources
                .getString(
                    R.string.age_pattern,
                    player.age,
                    itemView.context.resources.getQuantityString(R.plurals.age, player.age))
            binding.playerPosition.text = player.position.rusName;

            Glide.with(itemView)
                .load(player.photoUrl)
                .into(binding.icon)
        }
        fun setOnClick(action : () -> Unit){
            binding.root.setOnClickListener{
                //itemView.findNavController()?.navigate(R.id.action_navigation_list_to_navigation_card)
                action.invoke()
            }
        }

    }

    class AdHolder(private val binding: ItemAdBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(adItem : AdUIModel){
            binding.adText.text = adItem.text
        }
    }
    class BoardHolder(private val binding: ItemBoardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(board: Board){
            binding.text1.text = board.textBoard
            binding.text2.text = board.textBoard
            Glide.with(itemView)
                .load(board.photoUrlBoard)
                .into(binding.iconBoard)
        }
    }

    companion object{
        const val PLAYER_ID = 0
        const val AD_ID = 1
        const val BOARD_ID = 2
    }
}