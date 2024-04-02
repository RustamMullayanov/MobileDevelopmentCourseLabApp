package com.example.mobiledevelopmentcourselabapp.presentation.view.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.ListMvpView
import com.example.mobiledevelopmentcourselabapp.ListPresenter
import com.example.mobiledevelopmentcourselabapp.LocalTime
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentSecondBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter.PlayersAdapter
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.CardFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.ItemUIModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.PlayerUiModel
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class SecondFragment : MvpAppCompatFragment(), ListMvpView {

    private var _binding: FragmentSecondBinding? = null
    private val playerAdapter by lazy { PlayersAdapter(presenter::onPlayerClicked) }
    private val binding get() = _binding!!

    @Inject//
    lateinit var  presenterProvider: Provider<ListPresenter>

    private val presenter by moxyPresenter{presenterProvider.get()}





    @Inject
    lateinit var localTime: LocalTime
    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Обращайся к элементам View здесь
        return root
    }

    companion object{
        const val CARD_PLAYER_KEY = "PLAYER"
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        //binding.playerList.adapter = playerAdapter
        //playerAdapter.updateItems(generator.get().generate())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun navigateToPlayer(playerUiModel: PlayerUiModel) {  //
        val bundle = bundleOf(SecondFragment.CARD_PLAYER_KEY to playerUiModel)
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_navigation_card, bundle)
    }

    override fun showPlayers(player: List<ItemUIModel>) {
        binding.playerList.adapter = playerAdapter
        playerAdapter.updateItems(player)
    }
}