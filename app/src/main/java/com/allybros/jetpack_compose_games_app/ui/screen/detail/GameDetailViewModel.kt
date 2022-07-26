package com.allybros.jetpack_compose_games_app.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allybros.jetpack_compose_games_app.entity.detail.GamesDetailResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


/**
 * Created by orcun on 26.07.2022
 */

class GameDetailViewModel(
    private val gameDetailRepository: GameDetailRepository
) : ViewModel(), KoinComponent {

    private var _gameLiveData = MutableStateFlow(GamesDetailResponse())
    val gameLiveData: StateFlow<GamesDetailResponse> get() = _gameLiveData


    fun getDetail(id: String) {
        gameDetailRepository.id = id
        viewModelScope.launch {
            gameDetailRepository.getDetail.collect {
                if (it != null) {
                    _gameLiveData.value = it
                }
            }
        }
    }
}