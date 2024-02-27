package com.example.mobiledevelopmentcourselabapp.presentation.view.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentSecondBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter.PlayersAdapter
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.generator.Generator

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val playerAdapter by lazy { PlayersAdapter() }
    private val binding get() = _binding!!

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playerList.adapter = playerAdapter
        playerAdapter.updateItems(Generator.generate())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}