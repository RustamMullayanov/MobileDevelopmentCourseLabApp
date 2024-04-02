package com.example.mobiledevelopmentcourselabapp.presentation.view.second.model

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.CardMvpView
import com.example.mobiledevelopmentcourselabapp.CardPresenter
import com.example.mobiledevelopmentcourselabapp.ListMvpView
import com.example.mobiledevelopmentcourselabapp.ListPresenter
import com.example.mobiledevelopmentcourselabapp.LocalTime
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentCardBinding
import com.example.mobiledevelopmentcourselabapp.di.AppComponent
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.SecondFragment
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.lang.Appendable
import javax.inject.Inject
import javax.inject.Provider

class CardFragment : MvpAppCompatFragment(), CardMvpView {
    //СВЯЗЫВАЕМ ВЕРСТКУ



    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!  //если два восклицательных знаков значит переменная не будет ровна null
    private val player by lazy { arguments?.getSerializable(SecondFragment.CARD_PLAYER_KEY) as? PlayerUiModel }

    @Inject
    lateinit var  presenterProvider: Provider<CardPresenter>

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
        _binding = FragmentCardBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        player?.let {
            binding.name.text = it.name
            binding.number.text = it.number.toString()
            binding.ageValue.text = it.age.toString()

            binding.positionTitle.text = it.position.rusName
            binding.teamValue.text = it.team

            binding.statisticGoal.text = it.goalCount.toString() + " Голов"
            binding.statisticGame.text = it.gameCount.toString() + " Игр"
            binding.statisticPeredach.text = it.assistCount.toString() + " Передач"
            binding.statisticYellowCard.text = it.yellowCardCount.toString() + " Желтых"
            binding.statisticRedCard.text = it.redCardCount.toString() + " Красных"
            binding.timeValue.text = localTime.datetime().toString()




            Glide.with(this)
                .load(it.photoUrl)
                .circleCrop()
                .into(binding.icon)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.card_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_share) {
            presenter.onShareButton(player!!)

            return true
        }
       return false
    }


    companion object{
        const val CARD_PLAYER_KEY = "PLAYER"
    }

    override fun sharePlayer(player: String) {
        var shareIntent = Intent().apply{
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, player )
        }

        val chooser = Intent.createChooser(shareIntent, "Данные игрока")
        startActivity(chooser)
    }
}