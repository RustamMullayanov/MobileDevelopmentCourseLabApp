package com.example.mobiledevelopmentcourselabapp.presentation.view.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseFragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentListBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.adapter.PlayersAdapter
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.ItemUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.PlayerUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter.EditPresenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter.ListPresenter
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class ListFragment : BaseFragment(), ListMvpView {

    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    private val adapter by lazy { PlayersAdapter(presenter::onPlayerClicked) }

    @Inject
    lateinit var presenterProvider: Provider<ListPresenter>

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
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.playersList.adapter = adapter

        setFragmentResultListener(DEFAULT_RESULT_KEY) { key, bundle ->
            if (bundle.containsKey(EditPresenter.NEED_TO_UPDATE)) presenter.update()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                navigateToEdit()
                true
            }
            else -> true
        }
    }

    override fun navigateToPlayer(player: PlayerUiModel) {
        val bundle = bundleOf(CardFragment.CARD_PLAYER_KEY to player)
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_navigation_card, bundle)
    }

    private fun navigateToEdit() {
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_edit)
    }

    override fun showPlayers(players: List<ItemUiModel>) {
        adapter.updateItems(players)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}