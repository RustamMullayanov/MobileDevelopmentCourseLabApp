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
        // Обращайся к элементам View здесь

        context?.let {
            Glide
                .with(it)
                .asBitmap()
                .load("https://img.championat.com/s/732x488/news/big/x/m/zaripov-podal-dokumenty-dlya-uchastiya-v-predvaritelnom-golosovanii-edinoj-rossii_1713960113310716593.jpg")
                .placeholder(AppCompatResources.getDrawable(it, R.drawable.photo))
                .into(binding.mainPhoto)
        }

        binding.likeBtn.setOnClickListener {
            score++
            binding.likeResult.text = score.toString()

        }
        binding.dislikeBtn.setOnClickListener {
            score--
            binding.likeResult.text = score.toString()



        }

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Lifecycles", "${this::class.simpleName} - onAttach")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycles", "${this::class.simpleName} - onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycles", "${this::class.simpleName} - onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycles", "${this::class.simpleName} - onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycles", "${this::class.simpleName} - onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycles", "${this::class.simpleName} - onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycles", "${this::class.simpleName} - onDestroy")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("Lifecycles", "${this::class.simpleName} - onDestroyView")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Lifecycles", "${this::class.simpleName} - onDetach")

    }
}