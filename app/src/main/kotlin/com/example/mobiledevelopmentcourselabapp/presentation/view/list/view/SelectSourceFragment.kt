package com.example.mobiledevelopmentcourselabapp.presentation.view.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentSelectSourceBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.SelectionType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectSourceFragment: BottomSheetDialogFragment() {

    private var binding: FragmentSelectSourceBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSelectSourceBinding.inflate(inflater, container, false)
            .also { this.binding = it }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.camera?.setOnClickListener { exitWithResult(SelectionType.CAMERA) }
        binding?.gallery?.setOnClickListener { exitWithResult(SelectionType.GALLERY) }
    }

    private fun exitWithResult(type: SelectionType) {
        setFragmentResult(SELECTION_KEY, bundleOf(SELECTED_VARIANT to type.name))
        dismissAllowingStateLoss()
    }

    companion object {
        const val SELECTION_KEY = "SELECTION_KEY"
        const val SELECTED_VARIANT = "SELECTED_VARIANT"
    }
}