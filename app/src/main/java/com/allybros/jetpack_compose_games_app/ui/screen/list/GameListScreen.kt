package com.allybros.jetpack_compose_games_app.ui.screen.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.allybros.jetpack_compose_games_app.entity.list.Result
import com.allybros.jetpack_compose_games_app.ui.theme.JetpackcomposegamesappTheme
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
            Text(
                text = it.name,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

//@Composable
//private fun ProductListItem(coffee: Coffee, navController: NavController) {
//    Row(modifier = Modifier.padding(all = 8.dp)) {
//        Image(
//            painter = rememberImagePainter("https://coffee.alexflipnote.dev/random"),
//            contentDescription = "Contact profile picture",
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape),
//            contentScale = ContentScale.Crop)
//
//        Spacer(modifier = Modifier.width(8.dp))
//
//        Column(modifier = Modifier.weight(2f)) {
//            Text(text = coffee.title, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, color = DarkCoffee)
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(text = coffee.ingredients.size.toString() + " ingredients", color = DarkCoffee)
//        }
//
//        Spacer(modifier = Modifier.width(8.dp))
//
//        Button(modifier = Modifier
//            .padding(6.dp)
//            .weight(1f),colors = ButtonDefaults.buttonColors(backgroundColor = Coffee)
//            , onClick = { navController.navigate("productdetail/${coffee.title}/${coffee.description}/${coffee.ingredients}/${coffee.id}") }) {
//            Text(text = "Detail", color = DarkCoffee)
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
private fun ProductListScreenPreview() {
    JetpackcomposegamesappTheme {
        GameListScreen(rememberNavController())
    }
}