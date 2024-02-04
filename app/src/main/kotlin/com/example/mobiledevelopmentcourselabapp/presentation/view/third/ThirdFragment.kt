package com.example.mobiledevelopmentcourselabapp.presentation.view.third

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Обращайся к элементам View здесь
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