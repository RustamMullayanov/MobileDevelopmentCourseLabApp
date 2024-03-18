package com.example.mobiledevelopmentcourselabapp.presentation.view.article.view

import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseMvpView
import com.example.mobiledevelopmentcourselabapp.domain.model.ChuckJokeEntity
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface ArticleView: BaseMvpView {
    fun setScore(score: String)
    fun setJoke(joke: ChuckJokeEntity)
    fun setUpdateTime(time: String)
    fun setJokeFromNinja(joke: String)
    fun setupSpinner(categories: List<String>)
    @OneExecution
    fun openWebView(link: String)
}