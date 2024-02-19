package com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter

import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.view.CardMvpView
import com.example.mobiledevelopmentcourselabapp.utils.orFalse
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CardPresenter @Inject constructor(): MvpPresenter<CardMvpView>() {

    private var isCommentsOpen = false

    fun onCommentTitleClicked() {
        isCommentsOpen = isCommentsOpen.not()
        viewState.setHiddenGroupVisibility(isCommentsOpen)
        viewState.setCommentChevronIcon(
            if (isCommentsOpen) {
                R.drawable.chevron_up
            } else {
                R.drawable.chevron_down
            }
        )
    }

    fun onCommentChanged(text: CharSequence?) {
        viewState.setSendButtonEnabled(text?.isNotBlank().orFalse())
    }
}