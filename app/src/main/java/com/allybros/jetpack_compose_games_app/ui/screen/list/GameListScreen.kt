package com.allybros.jetpack_compose_games_app.ui.screen.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.allybros.jetpack_compose_games_app.entity.common.AppConstants
import com.allybros.jetpack_compose_games_app.entity.list.Result
import com.allybros.jetpack_compose_games_app.ui.theme.*
import org.koin.androidx.compose.getViewModel


/**
 * Created by orcun on 25.07.2022
 */

@Composable
fun GameListScreen(navController: NavController) {
    ProductList(navController = navController)
}

@Composable
fun ProductList(
    navController: NavController,
    viewModel: GameListViewModel = getViewModel()
) {
    val gameListState by viewModel.gameListLiveData.collectAsState()

    Scaffold {
        GameList(gameList = gameListState, navController = navController)
    }
}

@Composable
private fun GameList(gameList: List<Result>, navController: NavController) {
    LazyColumn{
        items(gameList){
            GameListItem(it, navController)
        }
    }
}

@Composable
private fun GameListItem(item: Result, navController: NavController) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        OutlinedButton(modifier = Modifier
            .padding(6.dp)
            .weight(1f),
            onClick = { onGameClicked(item, navController) },

        )
        {
            AsyncImage(
                model = item.background_image,
                contentDescription = item.slug,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    color = Purple900
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.rating.toString(),
                    color = Blue700,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
        }

    }
}

fun onGameClicked(item: Result, navController: NavController) {
    navController.navigate(
        AppConstants.GAME_DETAIL_WITH_ARGUMENTS.replace(
            "{id}",
            item.id.toString()
        ))
}

@Preview(showBackground = true)
@Composable
private fun ProductListScreenPreview() {
    JetpackcomposegamesappTheme {
        GameListScreen(rememberNavController())
    }
}