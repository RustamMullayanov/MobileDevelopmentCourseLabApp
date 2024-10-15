package com.example.mobiledevelopmentcourselabapp.presentation.view.mv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentMvBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.mv.viewModel.MvvmDemoViewModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.mv.viewModel.event.DemoEvent
import kotlinx.coroutines.launch

class MvvmFragment: Fragment() {
    private lateinit var binding: FragmentMvBinding

    private val viewModel by viewModels<MvvmDemoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMvBinding.inflate(inflater, container, false)
            .also { this.binding = it }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.heightLayout.setEndIconOnClickListener { viewModel.sendEvent(DemoEvent.HeightDoneClicked) }
        binding.weightLayout.setEndIconOnClickListener { viewModel.sendEvent(DemoEvent.WeightDoneClicked) }

        binding.height.doOnTextChanged { text, _, _, _ -> viewModel.sendEvent(DemoEvent.HeightChanged(text)) }
        binding.weight.doOnTextChanged { text, _, _, _ -> viewModel.sendEvent(DemoEvent.WeightChanged(text)) }

        binding.repeat.setOnClickListener { viewModel.sendEvent(DemoEvent.RepeatClicked) }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { state ->
                    binding.heightLayout.isVisible = state.heightVisibility
                    binding.weightLayout.isVisible = state.weightVisibility
                    binding.resultLayout.isVisible = state.resultVisibility
                    binding.heightLayout.isEndIconVisible = state.heightDoneEnabled
                    binding.weightLayout.isEndIconVisible = state.weightDoneEnabled
                    binding.result.text = state.result
                }
            }
        }
    }
}