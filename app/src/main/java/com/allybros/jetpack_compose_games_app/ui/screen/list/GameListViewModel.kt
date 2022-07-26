package com.allybros.jetpack_compose_games_app.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allybros.jetpack_compose_games_app.entity.common.PageType
import com.allybros.jetpack_compose_games_app.entity.list.GameListResponse
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


    private var _gameListLiveData = MutableStateFlow(GameListResponse())
    val responseLiveData: StateFlow<GameListResponse> get() = _gameListLiveData

    var next = ""
    var previous = ""

    init {
        viewModelScope.launch {
            getGameList()
        }
    }

    private fun getGameList(page: Int? = 1, pageSize: Int? = 10) {
        if (page != null) {
            gameListRepository.page = page
        }
        if (pageSize != null) {
            gameListRepository.pageSize = pageSize
        }

        viewModelScope.launch {
            gameListRepository.getGameList.collect {
                if (it != null) {
                    _gameListLiveData.value = it
                    setPageLinks(it)
                }
            }
        }
    }

    private fun setPageLinks(it: GameListResponse) {
        if(!it.next.isNullOrBlank()){
            next = it.next
        }
        if(!it.previous.isNullOrBlank()){
            previous = it.previous
        }
    }

    fun getGamePage(type: PageType){
        when(type){
            PageType.NEXT->{
                gameListRepository.url = next
            }
            PageType.PREVIOUS->{
                gameListRepository.url = previous
            }
        }

        viewModelScope.launch {
            gameListRepository.getGameListPage.collect {
                it?.results?.toList()?.let { it1 ->
                    it.results.addAll(_gameListLiveData.value.results!!)
                    it.results.sortBy { it.released }
                    _gameListLiveData.value = it
                }
                if (it != null) {
                    setPageLinks(it)
                }
            }
        }
    }
}