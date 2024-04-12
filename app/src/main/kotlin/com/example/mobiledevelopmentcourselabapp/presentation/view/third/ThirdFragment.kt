package com.example.mobiledevelopmentcourselabapp.presentation.view.third

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentThirdBinding
import com.example.mobiledevelopmentcourselabapp.domain.model.ChuckJokeEntity
import com.example.mobiledevelopmentcourselabapp.presentation.view.presenter.ThirdPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


class ThirdFragment : MvpAppCompatFragment(), ThirdView {

    private var _binding: FragmentThirdBinding? = null

    private val binding get() = _binding!!
    @Inject
    lateinit var  presentProvider: Provider<ThirdPresenter>
    private val presenter by moxyPresenter { presentProvider.get() }
    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Обращайся к элементам View здесь
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.update.setOnClickListener()
        {
            presenter.loadJoke()
        }
        binding.goToWeb.setOnClickListener()
        {
            presenter.open()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun setJoke(jokeEntity: ChuckJokeEntity) {
        binding.firstText.text = jokeEntity.value
    }

    override fun navigateToSite(jokeEntity: ChuckJokeEntity) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(jokeEntity.url))
        startActivity(browserIntent)
    }

}