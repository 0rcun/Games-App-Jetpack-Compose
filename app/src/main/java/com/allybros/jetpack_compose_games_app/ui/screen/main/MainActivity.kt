package com.allybros.jetpack_compose_games_app.ui.screen.main

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.allybros.jetpack_compose_games_app.entity.common.AppConstants
import com.allybros.jetpack_compose_games_app.ui.screen.list.GameListScreen
import com.allybros.jetpack_compose_games_app.ui.theme.JetpackcomposegamesappTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackcomposegamesappTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackcomposegamesappTheme {
        GameListScreen(rememberNavController())
    }
}

@Composable
private fun MainScreen(){
    val navController = rememberNavController()
    LaunchedEffect(Dispatchers.Main){
        delay(2000)
        navController.navigate(AppConstants.GAME_LIST)
    }
    NavHost(navController = navController, startDestination = AppConstants.GAME_LIST) {
        composable(AppConstants.GAME_LIST) { GameListScreen(navController) }
//        composable(AppConstants.GAME_DETAIL_WITH_ARGUMENTS ,
//            arguments = listOf(
//                navArgument("id") { type = NavType.StringType })
//        ) { backStackEntry ->
//            GameDetailScreen(backStackEntry.arguments?.getString("id").toString())
//        }
    }
}