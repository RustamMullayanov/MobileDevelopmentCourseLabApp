package com.example.mobiledevelopmentcourselabapp.core.presentation

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.mobiledevelopmentcourselabapp.R
import moxy.MvpAppCompatFragment

open class BaseFragment: MvpAppCompatFragment(), BaseMvpView {

    private var loadingDialog: Dialog? = null

    override fun setLoadingVisibility(isVisible: Boolean) {
        if (isVisible) {
            loadingDialog?.dismiss()
            loadingDialog =
                AlertDialog.Builder(requireActivity())
                    .setMessage(getString(R.string.loading))
                    .setCancelable(false)
                    .create()
            loadingDialog?.show()
        } else {
            loadingDialog?.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loadingDialog = null
    }

    override fun back() {
        findNavController().navigateUp()
    }

    override fun backWithResult(result: Bundle) {
        setFragmentResult(DEFAULT_RESULT_KEY, result)
        findNavController().navigateUp()
    }

    override fun showError(throwable: Throwable) {
        context?.let { Toast.makeText(it, throwable.localizedMessage, Toast.LENGTH_LONG).show() }
    }


    companion object {
        @JvmStatic
        protected val DEFAULT_RESULT_KEY = "DEFAULT_RESULT_KEY"
    }
}