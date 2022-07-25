package com.allybros.jetpack_compose_games_app.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allybros.jetpack_compose_games_app.entity.list.GameListResponse
import com.allybros.jetpack_compose_games_app.entity.list.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


/**
 * Created by orcun on 25.07.2022
 */

class GameListViewModel(
    private val gameListRepository: GameListRepository
) : ViewModel(), KoinComponent {


    private var _gameListLiveData = MutableStateFlow<List<Result>>(listOf())
    val gameListLiveData: StateFlow<List<Result>> get() = _gameListLiveData


    init {
        viewModelScope.launch {
            if (gameListLiveData.value.isEmpty())
                getGameList(2,100)
        }
    }

    private fun getGameList(page: Int? = 1, pageSize: Int? = 10) {
        if (page != null) {
            gameListRepository.page = page
        }
        if (pageSize != null) {
            gameListRepository.pageSize = pageSize
        }

        viewModelScope.launch{
            gameListRepository.getGameList.collect {
                if (it != null) {
                    _gameListLiveData.value = it
                }
            }
        }
    }
}