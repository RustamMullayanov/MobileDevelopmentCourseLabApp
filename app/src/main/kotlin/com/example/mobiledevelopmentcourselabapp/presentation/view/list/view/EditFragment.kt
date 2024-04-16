package com.example.mobiledevelopmentcourselabapp.presentation.view.list.view

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseFragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentEditBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter.EditPresenter
import moxy.ktx.moxyPresenter
import java.io.File
import javax.inject.Inject
import javax.inject.Provider

class EditFragment: BaseFragment(), EditView {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenterProvider: Provider<EditPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    private var pickMedia : ActivityResultLauncher<PickVisualMediaRequest>? = null
    private var capturePhoto =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            Glide.with(binding.avatar).load(it).into(binding.avatar)
        }

    val imageCapture = ImageCapture.Builder()
        //.setTargetRotation(view.display.rotation)
        .build()

    private val photoFile by lazy {
        File.createTempFile(
            "IMG_",
            ".jpg",
            requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )
    }

    private val photoUri by lazy {
        FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            photoFile
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent?.inject(this)
        pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                presenter.onImageSelected(uri)
            }
//        cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, imageCapture,
//            imageAnalysis, preview)

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

        binding.avatar.setOnClickListener {
            //capturePhoto.launch(photoUri)
            pickMedia?.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setAvatar(uri: Uri) {
        Glide
            .with(binding.avatar)
            .load(uri)
            .into(binding.avatar)
    }
}