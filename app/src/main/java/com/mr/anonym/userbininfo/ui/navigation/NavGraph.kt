package com.mr.anonym.userbininfo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mr.anonym.userbininfo.ui.screens.SearchHistoryScreen
import com.mr.anonym.userbininfo.ui.screens.SearchInfoScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screens.SearchInfoScreen.route
    ){
        composable(Screens.SearchInfoScreen.route) {
            SearchInfoScreen(navController)
        }
        composable(Screens.SearchHistoryScreen.route) {
            SearchHistoryScreen(navController)
        }
    }
}