package com.example.mobiledevelopmentcourselabapp.presentation.view.mv.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseFragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentMvBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.mv.fragment.view.MvpDemoView
import com.example.mobiledevelopmentcourselabapp.presentation.view.mv.presenter.MvpDemoPresenter
import moxy.ktx.moxyPresenter

class MvpDemoFragment: BaseFragment(), MvpDemoView {
    private lateinit var binding: FragmentMvBinding

    private val presenter by moxyPresenter { MvpDemoPresenter() }

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

        binding.height.doOnTextChanged { text, _, _, _ -> presenter.onHeightChanged(text) }
        binding.heightLayout.setEndIconOnClickListener { presenter.onHeightDoneClick() }
        binding.weight.doOnTextChanged { text, _, _, _ -> presenter.onWeightChanged(text) }
        binding.weightLayout.setEndIconOnClickListener { presenter.onWeightDoneClick() }
        binding.repeat.setOnClickListener { presenter.onRepeatClick() }
    }

    override fun setHeightVisibility(isVisible: Boolean) {
        binding.heightLayout.isVisible = isVisible
    }

    override fun setHeightDoneEnabled(isEnabled: Boolean) {
        binding.heightLayout.isEndIconVisible = isEnabled
    }

    override fun setWeightVisibility(isVisible: Boolean) {
        binding.weightLayout.isVisible = isVisible
    }

    override fun setWeightDoneEnabled(isEnabled: Boolean) {
        binding.weightLayout.isEndIconVisible = isEnabled
    }

    override fun setResultVisibility(isVisible: Boolean) {
        binding.resultLayout.isVisible = isVisible
    }

    override fun setResult(result: String) {
        binding.result.text = result

        AlertDialog.Builder(requireContext())
            .setMessage(result)
            .show()
    }
}