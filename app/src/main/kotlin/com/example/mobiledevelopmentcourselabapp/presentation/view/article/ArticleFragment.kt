package com.example.mobiledevelopmentcourselabapp.presentation.view.article

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null

    private var score = 0

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("life_research", "${this::class.simpleName} - onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("life_research", "${this::class.simpleName} - onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            Glide
                .with(it)
                .load("https://i.imgur.com/s0q7gtN.jpg")
                .placeholder(AppCompatResources.getDrawable(it, R.drawable.photo))
                .circleCrop()
                .into(binding.authorImage)

            Glide
                .with(it)
                .load("https://i.playground.ru/p/T5ZpgZnbK92n-lWs5VG56Q.png?760-auto")
                .placeholder(AppCompatResources.getDrawable(it, R.drawable.photo))
                .centerCrop()
                .into(binding.mainPhoto)

            Glide
                .with(it)
                .load("https://i.playground.ru/p/PJsRH6Fge-Kl4YjM5S4ONg.png?255x255")
                .placeholder(AppCompatResources.getDrawable(it, R.drawable.photo))
                .centerCrop()
                .into(binding.testPhotoOne)

            Glide
                .with(it)
                .load("https://i.playground.ru/p/aDR4rT1C_D97GYQgyzb5pw.png?255x255")
                .placeholder(AppCompatResources.getDrawable(it, R.drawable.photo))
                .centerCrop()
                .into(binding.testPhotoTwo)
        }

        binding.likeBtn.setOnClickListener {
            score++
            binding.likeResult.text = score.toString()
        }

        binding.dislikeBtn.setOnClickListener {
            score--
            binding.likeResult.text = score.toString()
        }
        Log.d("life_research", "${this::class.simpleName} - onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("life_research", "${this::class.simpleName} - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life_research", "${this::class.simpleName} - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life_research", "${this::class.simpleName} - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life_research", "${this::class.simpleName} - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life_research", "${this::class.simpleName} - onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("life_research", "${this::class.simpleName} - onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("life_research", "${this::class.simpleName} - onDestroyDetach")
    }
}