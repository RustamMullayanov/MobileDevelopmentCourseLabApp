package com.example.mobiledevelopmentcourselabapp.presentation.view.list.view

import androidx.annotation.DrawableRes
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface CardMvpView: MvpView {
    fun setupTextLinks(text: String, links: Map<String, () -> Unit>)
    fun setHiddenGroupVisibility(isVisible: Boolean)
    fun setCommentChevronIcon(@DrawableRes icon: Int)
    fun setSendButtonEnabled(isEnabled: Boolean)
    fun setMessageError(error: String)
    @AddToEnd
    fun addComment(comment: String)
    @OneExecution
    fun showSnackbar()
    @OneExecution
    fun clearCommentText()
}
