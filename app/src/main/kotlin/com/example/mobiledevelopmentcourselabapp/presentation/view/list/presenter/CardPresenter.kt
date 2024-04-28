package com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.view.CardMvpView
import com.example.mobiledevelopmentcourselabapp.utils.orFalse
import com.rajat.pdfviewer.PdfViewerActivity
import com.rajat.pdfviewer.util.saveTo
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CardPresenter @Inject constructor(
    private val context: Context,
    private val mediaPlayer: MediaPlayer
): MvpPresenter<CardMvpView>() {
    private var isCommentsOpen = false
    private var message: String? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setupTextLinks(
            text = context.getString(R.string.textLinks),
            links = mapOf(
                context.getString(R.string.linkSocial) to ::onSocialLinkClicked,
                context.getString(R.string.linkResume) to ::openPdf
            )
        )

        mediaPlayer.start()
    }

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
                viewState.clearCommentText()
            }
        }
    }

    private fun onSocialLinkClicked() {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://vk.com/institutdo"))
        context.startActivity(intent)
    }

    private fun openPdf() {
        context.startActivity(
            PdfViewerActivity.launchPdfFromUrl(
                context = this.context,
                pdfUrl = "https://sinara-finance.ru/upload/iblock/862/bwnbgrwjbzkti8tsgh0fbfjym5j4tz1k/Dogovor-o-brokerskom-obsluzhivanii_46.pdf",
                pdfTitle = "Резюме",
                saveTo = saveTo.DOWNLOADS,
                enableDownload = true
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.pause()
    }
}