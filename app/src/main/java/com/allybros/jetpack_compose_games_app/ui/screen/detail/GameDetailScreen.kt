package com.allybros.jetpack_compose_games_app.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.allybros.jetpack_compose_games_app.ui.theme.JetpackcomposegamesappTheme
import org.koin.androidx.compose.getViewModel


/**
 * Created by orcun on 26.07.2022
 */


@Composable
fun GameDetailScreen(id: String) {
    GameDetail(id)
}

@Composable
fun GameDetail(
    id: String,
    viewModel: GameDetailViewModel = getViewModel()
) {
    viewModel.getDetail(id)
    val detailState by viewModel.gameLiveData.collectAsState()
    Scaffold {
        Column(
            modifier = Modifier
            .verticalScroll(rememberScrollState())
        ) {
            DetailRow { AsyncImage(model = detailState.background_image, contentDescription = null) }
            DetailRow { Text(text = "Rating : ".plus(detailState.rating.toString())) }
            DetailRow { Text(text = "Released Date : ".plus(detailState.released.toString())) }
            DetailRow { Text(text = "Description : ".plus(detailState.description_raw.toString())) }
        }
    }
}

@Composable
fun DetailRow(content: @Composable () -> Unit) {
    Row(Modifier.padding(4.dp)) {
        content.invoke()
    }
}


@Preview(showBackground = true)
@Composable
private fun ProductListScreenPreview() {
    JetpackcomposegamesappTheme {
        GameDetailScreen("")
    }
}