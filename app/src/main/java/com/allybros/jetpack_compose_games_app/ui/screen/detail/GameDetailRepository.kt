package com.allybros.jetpack_compose_games_app.ui.screen.detail

import com.allybros.jetpack_compose_games_app.network.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by orcun on 26.07.2022
 */

class GameDetailRepository(private val apiFactory: ApiFactory)  {
    var id = ""

    val getDetail = flow {
        val response = apiFactory.getGameDetail(id)
        emit(response.body())

    }.flowOn(Dispatchers.IO)
}