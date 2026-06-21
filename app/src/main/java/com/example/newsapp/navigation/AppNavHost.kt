package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.ui.screen.NewsDetailScreen
import com.example.newsapp.ui.screen.NewsScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.NewsList.route
    ) {

        composable(Screen.NewsList.route) {
            NewsScreen(navController)
        }

        composable(
            route = Screen.NewsDetail.route,
            arguments = listOf(
                navArgument("newsId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val newsId =
                backStackEntry.arguments
                    ?.getInt("newsId")
                    ?: 0

            NewsDetailScreen(
                newsId = newsId
            )
        }
    }
}