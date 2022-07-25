package com.allybros.jetpack_compose_games_app.network

import com.allybros.jetpack_compose_games_app.entity.common.API_KEY
import com.allybros.jetpack_compose_games_app.entity.detail.GamesDetailResponse
import com.allybros.jetpack_compose_games_app.entity.list.GameListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiFactory {

    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id : String,
        @Query("key") key : String? = API_KEY
    ) : Response<GamesDetailResponse>

    @GET("games")
    suspend fun getGameList(
        @Query("page_size") pageSize : Int,
        @Query("page") page : Int,
        @Query("key") key : String? = API_KEY
    ) : Response<GameListResponse>

}
