package com.example.mobiledevelopmentcourselabapp.presentation.view.presenter
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.CardMvpView
import com.example.mobiledevelopmentcourselabapp.presentation.view.utils.orFalse
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CardPresenter @Inject constructor(): MvpPresenter<CardMvpView>() {
    private var isCommentsOpen = false
    private var message: String? = null

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
        message = text.toString()
        viewState.setSendButtonEnabled(text?.isNotBlank().orFalse())
        viewState.setMessageError("")
    }

    fun onSendButtonClicked() {
        if (message?.equals("ошибка").orFalse()) {
            viewState.setMessageError("Это ошибка!")
        } else {
            message?.let {
                viewState.addComment(it)
                viewState.showSnackbar()
                viewState.setCommentText("")
            }
        }
    }
}