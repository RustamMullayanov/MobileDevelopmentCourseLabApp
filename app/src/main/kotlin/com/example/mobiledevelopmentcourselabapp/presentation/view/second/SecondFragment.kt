package com.example.mobiledevelopmentcourselabapp.presentation.view.second
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentListBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.presenter.ListPresenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter.NeurosAdapter
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.ItemUIModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.NeuroUIClass
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider
class SecondFragment : MvpAppCompatFragment(), ListMvpView {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val neuroAdapter by lazy { NeurosAdapter(presenter::onNeuroClicked)}
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
        binding.neuroList.adapter = neuroAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun showNeuros(neuros: List<ItemUIModel>) {
        neuroAdapter.updateItems(neuros)
    }

    override fun navigateToNeuro(neuro: NeuroUIClass) {
        val bundle = bundleOf(CardFragment.CARD_NEURO_KEY to neuro)
        view?.findNavController()?.navigate(R.id.action_to_navigation_card, bundle)
    }
}