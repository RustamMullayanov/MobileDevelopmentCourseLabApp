package com.example.mobiledevelopmentcourselabapp.core.presentation

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

open class BasePresenter<V : BaseMvpView> : MvpPresenter<V>() {
    private val compositeDisposable = CompositeDisposable()

    private var loadingCounter = 0

    fun Disposable.disposeOnDestroy() {
        compositeDisposable.add(this)
    }

    fun <T : Any> Single<T>.withLoadingView(view: BaseMvpView) =
        this
            .doOnSubscribe {
                loadingCounter++
                if (loadingCounter == 1) view.setLoadingVisibility(true)
            }
            .doFinally {
                loadingCounter--
                if (loadingCounter == 0) view.setLoadingVisibility(false)
            }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}