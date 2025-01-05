package com.mr.anonym.userbininfo.ui.navigation

sealed class Screens(val route:String) {
    data object SearchInfoScreen: Screens(route = "SearchInfoScreen")
    data object SearchHistoryScreen:Screens(route = "SearchHistoryScreen")
}