package com.example.mobiledevelopmentcourselabapp.presentation.view.list.view

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseFragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentEditBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.SelectionType
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter.EditPresenter
import moxy.ktx.moxyPresenter
import java.io.File
import java.util.Date
import javax.inject.Inject
import javax.inject.Provider

class EditFragment: BaseFragment(), EditView {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenterProvider: Provider<EditPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    private var pickMedia : ActivityResultLauncher<PickVisualMediaRequest>? = null

    private var imageUri: Uri? = null

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted.not()) {
                val dialog = AlertDialog.Builder(requireContext())
                    .setMessage("Необходимо разрешение")
                    .setCancelable(false)
                    .setPositiveButton("OK") { _, _  ->
                        view?.findNavController()?.navigateUp()
                    }

                dialog.show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent?.inject(this)
        pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                presenter.onImageSelected(uri)
            }

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
            navigateToSelect()
        }

        checkFilesPermission()

        setFragmentResultListener(SelectSourceFragment.SELECTION_KEY) { _, bundle ->
            val source = bundle.getString(SelectSourceFragment.SELECTED_VARIANT)?.let { SelectionType.valueOf(it) }
            when (source) {
                SelectionType.CAMERA -> takePicture()
                SelectionType.GALLERY -> launchPicker()
                else -> {}
            }
        }
    }

    override fun checkFilesPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }

    private fun launchPicker() {
        pickMedia?.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
    }

    private fun navigateToSelect() {
        view?.findNavController()?.navigate(R.id.action_navigation_edit_to_select)
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

    private val mGetContent = registerForActivityResult<Uri, Boolean>(
        ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success) {
            presenter.onImageSelected(imageUri)
        }
    }

    private fun takePicture() {
        val baseDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val pictureFile = File(baseDir, "picture_${Date().time}.jpg")
        imageUri = FileProvider.getUriForFile(
            requireContext(),
            requireContext().packageName + ".provider",
            pictureFile
        )
        mGetContent.launch(imageUri)
    }
}