package com.allybros.jetpack_compose_games_app.ui.screen.list

import com.allybros.jetpack_compose_games_app.network.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by orcun on 25.07.2022
 */

class GameListRepository(private val apiFactory: ApiFactory) {
    var pageSize = 10
    var page = 1
    var url = ""

    val getGameList = flow {
        val gameListResponse = apiFactory.getGameList(page = page, pageSize = pageSize)
        emit(gameListResponse.body())
    }.flowOn(Dispatchers.IO)

    val getGameListPage = flow {
        val gameListResponse = apiFactory.getGameList(url)
        emit(gameListResponse.body())

    }.flowOn(Dispatchers.IO)
}