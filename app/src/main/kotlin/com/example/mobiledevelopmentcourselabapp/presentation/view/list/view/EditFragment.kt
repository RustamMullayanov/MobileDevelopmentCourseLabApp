package com.example.mobiledevelopmentcourselabapp.presentation.view.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseFragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentEditBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter.EditPresenter
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class EditFragment: BaseFragment(), EditView {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenterProvider: Provider<EditPresenter>

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
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.done.setOnClickListener {
            presenter.onDoneClicked(
                name = binding.name.text.toString(),
                number = binding.number.text.toString(),
                position = binding.positionGroup.checkedRadioButtonId
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}