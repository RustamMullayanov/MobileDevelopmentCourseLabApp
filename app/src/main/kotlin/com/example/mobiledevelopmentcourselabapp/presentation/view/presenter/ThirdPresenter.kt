package com.example.mobiledevelopmentcourselabapp.presentation.view.presenter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import com.example.mobiledevelopmentcourselabapp.data.repository.ChuckRepository
import com.example.mobiledevelopmentcourselabapp.domain.model.ChuckJokeEntity
import com.example.mobiledevelopmentcourselabapp.presentation.view.third.ThirdView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class ThirdPresenter @Inject constructor(
    private val chuckRep: ChuckRepository
): MvpPresenter<ThirdView>() {
    private var joke: ChuckJokeEntity? = null

    @SuppressLint("CheckResult")
    fun loadJoke()
    {
        chuckRep.getRandomJoke()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe { jokeUrl ->
                joke=jokeUrl
                viewState.setJoke(jokeUrl) }
    }
    @SuppressLint("CheckResult")
    fun open() {
        joke?.let { viewState.navigateToSite(it) }
    }



}