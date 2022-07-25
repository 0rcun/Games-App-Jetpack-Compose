package com.allybros.jetpack_compose_games_app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import com.allybros.jetpack_compose_games_app.network.networkModule
import com.allybros.jetpack_compose_games_app.ui.screen.list.gameListModule

/**
 * Created by orcun on 25.07.2022
 */

class GamesApp: Application() {
    private val moduleList: List<Module> = listOf(
        networkModule,
        gameListModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GamesApp)
            modules(moduleList)
        }
    }
}