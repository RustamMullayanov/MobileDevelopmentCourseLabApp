package com.example.mobiledevelopmentcourselabapp.presentation.view.article.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentWebViewBinding

class WebViewFragment: Fragment() {
    private var _binding: FragmentWebViewBinding? = null

    private val binding get() = _binding!!

    private val link by lazy { arguments?.getString(WEB_VIEW_LINK_CODE) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        link?.let { binding.webView.loadUrl(it) }
    }


    companion object {
        const val WEB_VIEW_LINK_CODE = "WEB_VIEW_LINK_CODE"
    }
}