package com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mobiledevelopmentcourselabapp.databinding.ItemCommentMsgBinding

class CommentsAdapter: RecyclerView.Adapter<CommentsAdapter.CommentHolder>() {

    private val comments = mutableListOf<String>()

    fun addComment(comment: String) {
        comments.add(comment)
        notifyItemInserted(comment.length - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val binding =
            ItemCommentMsgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentHolder(binding)
    }

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        val item = comments[position]
        holder.bind(item)
    }

    inner class CommentHolder(private val binding: ItemCommentMsgBinding) : ViewHolder(binding.root) {
        fun bind(comment: String) {
            binding.root.text = comment
        }
    }
}