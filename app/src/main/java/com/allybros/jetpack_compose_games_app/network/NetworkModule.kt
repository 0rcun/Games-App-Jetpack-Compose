package com.allybros.jetpack_compose_games_app.network

import com.allybros.jetpack_compose_games_app.BuildConfig
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by orcun on 25.07.2022
 */

val networkModule: Module = module {
    single { createRetrofit()}
    factory { createWebService<ApiFactory>(get()) }

}

fun createRetrofit() : Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}
