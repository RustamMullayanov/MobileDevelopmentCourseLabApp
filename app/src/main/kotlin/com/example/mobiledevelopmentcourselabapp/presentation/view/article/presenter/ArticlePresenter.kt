package com.example.mobiledevelopmentcourselabapp.presentation.view.article.presenter

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder
import com.example.mobiledevelopmentcourselabapp.core.presentation.BasePresenter
import com.example.mobiledevelopmentcourselabapp.domain.interactor.ChuckInteractor
import com.example.mobiledevelopmentcourselabapp.presentation.view.article.view.ArticleView
import com.example.mobiledevelopmentcourselabapp.utils.formatDateTimeToUiDateTime
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class ArticlePresenter @Inject constructor(
    private val interactor: ChuckInteractor,
    private val context: Context
): BasePresenter<ArticleView>() {

    private val prefs = context.getSharedPreferences(PREFERENCES_STORE_NAME, Context.MODE_PRIVATE)
    private val store = RxPreferenceDataStoreBuilder(context, PREFERENCES_STORE_NAME).build()

    private val categoryKey = stringPreferencesKey(KEY_CATEGORY)

    private var categories: List<String> = emptyList()
    private var selectedCategory: String = prefs.getString(KEY_CATEGORY, "").orEmpty()
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
            .subscribe({
                categories = it
                viewState.setupSpinner(categories = it)
                updateSelectedCategory()
            }, viewState::showError)
            .disposeOnDestroy()

        store.data()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ prefs ->
                selectedCategory = prefs.get(categoryKey).orEmpty()
            }, viewState::showError)
            .disposeOnDestroy()
    }

    override fun attachView(view: ArticleView?) {
        super.attachView(view)
        updateSelectedCategory()
    }

    private fun updateSelectedCategory() {
        viewState.setSelectedCategory(
            selectedIndex = categories.indexOf(selectedCategory).takeIf { it > 0 }
        )
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
        with (prefs.edit()) {
            putString(KEY_CATEGORY, selectedCategory)
            apply()
        }

        store.updateDataAsync { prefsIn ->
            val mutablePreferences = prefsIn.toMutablePreferences()
            mutablePreferences.set(categoryKey, selectedCategory)
            Single.just(mutablePreferences)
        }.subscribe()
    }

    fun onOpenWebClicked() {
        link?.let { viewState.openWebView(it) }
    }

    companion object {
        private const val PREFERENCES_STORE_NAME = "APP_PREFERENCES"
        private const val KEY_CATEGORY = "KEY_CATEGORY"
    }
}