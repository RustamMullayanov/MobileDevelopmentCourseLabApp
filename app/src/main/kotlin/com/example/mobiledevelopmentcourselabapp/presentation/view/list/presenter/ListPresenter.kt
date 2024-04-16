package com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter

import com.example.mobiledevelopmentcourselabapp.core.presentation.BasePresenter
import com.example.mobiledevelopmentcourselabapp.data.model.PlayerDbEntity
import com.example.mobiledevelopmentcourselabapp.domain.interactor.PlayerInteractor
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.PlayerUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.view.ListMvpView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val interactor: PlayerInteractor
): BasePresenter<ListMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        update()
    }

    fun onPlayerClicked(playerUiModel: PlayerUiModel) {
        viewState.navigateToPlayer(playerUiModel)
    }

    private fun mapPlayers(dbList: List<PlayerDbEntity>): List<PlayerUiModel> {
        return dbList.map { dbPlayer ->
            PlayerUiModel(
                name = dbPlayer.name,
                number = dbPlayer.number,
                position = dbPlayer.position,
                photoUri = dbPlayer.avatarUri
            )
        }
    }

    fun update() {
        interactor.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { mapPlayers(it) }
            .subscribe(viewState::showPlayers, viewState::showError)
            .disposeOnDestroy()
    }
}