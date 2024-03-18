package com.example.mobiledevelopmentcourselabapp.presentation.view.article.presenter

import com.example.mobiledevelopmentcourselabapp.core.presentation.BasePresenter
import com.example.mobiledevelopmentcourselabapp.domain.interactor.ChuckInteractor
import com.example.mobiledevelopmentcourselabapp.presentation.view.article.view.ArticleView
import com.example.mobiledevelopmentcourselabapp.utils.formatDateTimeToUiDateTime
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ArticlePresenter @Inject constructor(
    private val interactor: ChuckInteractor
): BasePresenter<ArticleView>() {

    private var selectedCategory: String = ""
    private var link: String? = null

    private var score: Int = 0
        set(value) {
            viewState.setScore(field.toString())
            field = value
        }

    override fun onFirstViewAttach() {
        interactor.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .withLoadingView(viewState)
            .subscribe({ viewState.setupSpinner(it) }, viewState::showError)
            .disposeOnDestroy()
    }

    fun loadJoke() {
        interactor.getJokeByCategory(selectedCategory)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .withLoadingView(viewState)
            .subscribe({ chuckJoke ->
                link = chuckJoke.url
                viewState.setJoke(chuckJoke)
                viewState.setUpdateTime(formatDateTimeToUiDateTime(chuckJoke.updateTime))
            }, viewState::showError)
            .disposeOnDestroy()

//        interactor.getJokeFromNinja()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .withLoadingView(viewState)
//            .subscribe(viewState::setJokeFromNinja, viewState::showError)
//            .disposeOnDestroy()
    }

    fun onLikeClicked() = score++

    fun onDislikeClicked() = score--

    fun onCategorySelected(item: String) {
        selectedCategory = item
    }

    fun onOpenWebClicked() {
        link?.let { viewState.openWebView(it) }
    }
}