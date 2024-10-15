package com.example.mobiledevelopmentcourselabapp.presentation.view.mv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentMvBinding
import com.example.mobiledevelopmentcourselabapp.utils.orFalse
import com.example.mobiledevelopmentcourselabapp.utils.orZero

class NoMvFragment: Fragment() {
    private lateinit var binding: FragmentMvBinding

    private var h = 0
    private var w = 0

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

        binding.heightLayout.isEndIconVisible = false
        binding.weightLayout.isEndIconVisible = false

        binding.height.doOnTextChanged { text, _, _, _ ->
            h = text.toString().toIntOrNull().orZero()
            binding.heightLayout.isEndIconVisible = text?.isNotEmpty().orFalse()
        }

        binding.weight.doOnTextChanged { text, _, _, _ ->
            w = text.toString().toIntOrNull().orZero()
            binding.weightLayout.isEndIconVisible = text?.isNotEmpty().orFalse()
        }

        binding.heightLayout.setEndIconOnClickListener {
            binding.heightLayout.isVisible = false
            binding.weightLayout.isVisible = true
        }

        binding.weightLayout.setEndIconOnClickListener {
            binding.weightLayout.isVisible = false
            binding.resultLayout.isVisible = true

            val result = w * 1.0 / (h / 100.0 * h / 100)

            binding.result.text = "${String.format("%.2f", result)}, ${getVerdict(result)}"
        }

        binding.repeat.setOnClickListener {
            binding.resultLayout.isVisible = false
            binding.heightLayout.isVisible = true
        }
    }

    private fun getVerdict(index: Double): String {
        return when (index) {
            in (0.0..16.0) -> "Выраженный дефицит массы тела"
            in (16.0..18.5) -> "Недостаточная (дефицит) масса тела"
            in (18.5..25.0) -> "Норма"
            in (25.0..30.0) -> "Избыточная масса тела (предожирение)"
            in (30.0..35.0) -> "Ожирение первой степени"
            in (35.0..40.0) -> "Ожирение второй степени"
            in (40.0..Double.MAX_VALUE) -> "Ожирение третьей степени (морбидное)"
            else -> "Мои искренние соболезнования с днем Башкортостана"
        }
    }
}