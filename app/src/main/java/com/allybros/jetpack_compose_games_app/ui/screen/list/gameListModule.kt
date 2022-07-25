package com.allybros.jetpack_compose_games_app.ui.screen.list

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by orcun on 25.07.2022
 */

val gameListModule = module {
    viewModel { GameListViewModel(get()) }
    factory { GameListRepository(get()) }
}