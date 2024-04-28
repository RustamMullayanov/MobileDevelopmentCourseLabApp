package com.example.mobiledevelopmentcourselabapp.presentation.view.videoPlayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentVideoBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.videoPlayer.adapter.VideoAdapter

class VideoPlayerFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Обращайся к элементам View здесь
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.videoPager.adapter = VideoAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}