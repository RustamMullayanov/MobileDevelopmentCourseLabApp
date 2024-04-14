package com.example.mobiledevelopmentcourselabapp.presentation.view.article.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseFragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentArticleBinding
import com.example.mobiledevelopmentcourselabapp.domain.model.ChuckJokeEntity
import com.example.mobiledevelopmentcourselabapp.presentation.view.article.presenter.ArticlePresenter
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


class ArticleFragment : ArticleView, BaseFragment() {

    private var _binding: FragmentArticleBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var presenterProvider: Provider<ArticlePresenter>

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
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.thumbUp.setOnClickListener { presenter.onLikeClicked() }
        binding.thumbDown.setOnClickListener { presenter.onDislikeClicked() }
        binding.update.setOnClickListener { presenter.loadJoke() }
        binding.goToWeb.setOnClickListener { presenter.onOpenWebClicked() }
    }

    override fun setupSpinner(categories: List<String>) {
        val adapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, categories) }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val listener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val item = parent.getItemAtPosition(position) as String
                presenter.onCategorySelected(item)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spinner.onItemSelectedListener = listener
        binding.spinner.adapter = adapter
    }

    override fun openWebView(link: String) {
        val bundle = bundleOf(WebViewFragment.WEB_VIEW_LINK_CODE to link)
        view?.findNavController()?.navigate(R.id.action_navigation_article_to_webView, bundle)
    }

    override fun setScore(score: String) {
        binding.likeResult.text = score
    }

    override fun setJoke(joke: ChuckJokeEntity) {
        binding.firstText.text = joke.value
    }

    override fun setUpdateTime(time: String) {
        binding.dateTime.text = time
    }

    override fun setJokeFromNinja(joke: String) {
        binding.secondText.text = joke
    }

    override fun setSelectedCategory(selectedIndex: Int?) {
        selectedIndex?.let { binding.spinner.setSelection(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}