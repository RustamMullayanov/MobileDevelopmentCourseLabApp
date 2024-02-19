package com.example.mobiledevelopmentcourselabapp.presentation.view.list.view

import androidx.annotation.DrawableRes
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface CardMvpView: MvpView {

    fun setHiddenGroupVisibility(isVisible: Boolean)
    fun setCommentChevronIcon(@DrawableRes icon: Int)
    fun setSendButtonEnabled(isEnabled: Boolean)
}