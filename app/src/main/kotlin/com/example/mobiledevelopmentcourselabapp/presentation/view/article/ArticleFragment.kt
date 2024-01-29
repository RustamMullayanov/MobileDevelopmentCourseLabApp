package com.example.mobiledevelopmentcourselabapp.presentation.view.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null

    private var score = 0
        set(value) {
            field = value
            _binding?.likeResult?.text = value.toString()
        }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Обращайся к элементам View здесь

        binding.thumbUp.setOnClickListener { score++ }
        binding.thumbDown.setOnClickListener { score-- }

        Glide
            .with(requireContext())
            .load("https://img.championat.com/s/732x488/news/big/b/g/stal-izvesten-novyj-kandidat-na-zamenu-kloppu-v-liverpule_17065467721853904716.jpg")
            .into(binding.mainPhoto)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}