package com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter

import androidx.core.os.bundleOf
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.core.presentation.BasePresenter
import com.example.mobiledevelopmentcourselabapp.domain.interactor.PlayerInteractor
import com.example.mobiledevelopmentcourselabapp.domain.model.PlayerPosition
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.view.EditView
import com.example.mobiledevelopmentcourselabapp.utils.orZero
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class EditPresenter @Inject constructor(
    private val interactor: PlayerInteractor
) : BasePresenter<EditView>() {
    fun onDoneClicked(name: String, number: String, position: Int) {
        interactor.addPlayer(
            name = name,
            number = number.toIntOrNull().orZero(),
            position = when (position) {
                R.id.variantGoalkeeper -> PlayerPosition.GOALKEEPER
                R.id.variantDefender -> PlayerPosition.DEFENDER
                R.id.variantMidfielder -> PlayerPosition.MIDFIELD
                R.id.variantForward -> PlayerPosition.FORWARD
                else -> PlayerPosition.NONE
            }
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.backWithResult(bundleOf(NEED_TO_UPDATE to true))
            }, viewState::showError)
            .disposeOnDestroy()
    }

    companion object {
        const val NEED_TO_UPDATE = "NEED_TO_UPDATE"
    }
}