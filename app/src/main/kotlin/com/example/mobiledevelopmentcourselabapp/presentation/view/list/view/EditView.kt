package com.example.mobiledevelopmentcourselabapp.presentation.view.list.view

import android.net.Uri
import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseMvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface EditView: BaseMvpView {
    fun setAvatar(uri: Uri)
    @OneExecution
    fun checkFilesPermission()
}