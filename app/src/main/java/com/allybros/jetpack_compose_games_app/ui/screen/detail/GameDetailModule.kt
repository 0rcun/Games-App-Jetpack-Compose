package com.allybros.jetpack_compose_games_app.ui.screen.detail

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by orcun on 26.07.2022
 */


val gameDetailModule = module {
    viewModel { GameDetailViewModel(get()) }
    factory { GameDetailRepository(get()) }
}