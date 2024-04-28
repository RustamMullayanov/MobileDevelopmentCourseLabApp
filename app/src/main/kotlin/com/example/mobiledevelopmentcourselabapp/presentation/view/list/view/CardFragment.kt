package com.example.mobiledevelopmentcourselabapp.presentation.view.list.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentCardBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.adapter.CommentsAdapter
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.PlayerUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter.CardPresenter
import com.example.mobiledevelopmentcourselabapp.utils.makeLinks
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class CardFragment : MvpAppCompatFragment(), CardMvpView {

    private var _binding: FragmentCardBinding? = null

    private val binding get() = _binding!!

    private val player by lazy { arguments?.getSerializable(CARD_PLAYER_KEY) as? PlayerUiModel }

    private val adapter by lazy { CommentsAdapter() }

    @Inject
    lateinit var presenterProvider: Provider<CardPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        player?.let { player ->
            binding.name.text = player.name

            binding.name.text = player.name
            binding.number.text = player.number.toString()

            setStat(binding.ageValue, player.age, R.plurals.age)
            binding.positionValue.text = player.position.rusName
            binding.teamValue.text = player.team

            Glide
                .with(this)
                .load(player.getPhotoUri())
                .placeholder(R.drawable.account)
                .circleCrop()
                .into(binding.icon)

            setStat(binding.gamesCount, player.gamesCount, R.plurals.games)
            setStat(binding.goalsCount, player.goalsCount, R.plurals.goals)
            setStat(binding.assistsCount, player.assistsCount, R.plurals.assists)
            setStat(binding.yellowCardsCount, player.yellowCardCount, R.plurals.yellows)
            setStat(binding.redCardsCount, player.redCardsCount, R.plurals.reds)
        }

        binding.comments.commentsList.adapter = adapter

        binding.comments.commentTitle.setOnClickListener {
            presenter.onCommentTitleClicked()
        }

        binding.comments.commentInput.doOnTextChanged { text, _, _, _ ->
            presenter.onCommentChanged(text)
        }

        binding.comments.sendButton.setOnClickListener {
            presenter.onSendButtonClicked()
        }
        binding.comments.sendButton.isEnabled = false

        binding.icon.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW)
                    .setData(player?.getPhotoUri())
                    .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.card_menu, menu)
    }

    // Card Fragment
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                sharePlayerText()
                true
            }
            else -> true
        }
    }

    // https://developer.android.com/training/sharing/send
    private fun sharePlayerText() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, player.toString())
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun setStat(view: TextView, count: Int, @PluralsRes plural: Int) {
        view.text =
            resources.getString(
                R.string.count_pattern,
                count,
                resources.getQuantityString(plural, count)
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setupTextLinks(text: String, links: Map<String, () -> Unit>) {
        binding.pdfLink.text = text
        binding.pdfLink.makeLinks(links)
    }

    override fun setHiddenGroupVisibility(isVisible: Boolean) {
        binding.comments.hiddenGroup.isVisible = isVisible
    }

    override fun setCommentChevronIcon(@DrawableRes icon: Int) {
        binding.comments.chevron.setImageResource(icon)
    }

    override fun setSendButtonEnabled(isEnabled: Boolean) {
        binding.comments.sendButton.isEnabled = isEnabled
    }

    override fun setMessageError(error: String) {
        binding.comments.commentInputLayout.error = error
    }

    override fun addComment(comment: String) {
        adapter.addComment(comment)
    }

    override fun clearCommentText() {
        binding.comments.commentInput.setText("")
    }

    override fun showSnackbar() {
        Snackbar.make(
            requireContext(),
            binding.root,
            "Сообщение отправлено",
            Snackbar.LENGTH_LONG
        ).show()
    }

    companion object {
        const val CARD_PLAYER_KEY = "PLAYER"
    }
}