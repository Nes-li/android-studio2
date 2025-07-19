package com.example.kuaforhatirlatici.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { AnaSayfaWithDrawer() }
        // Diğer rotalar burada tanımlanır
    }
}

@Composable
fun AnaSayfaWithDrawer() {
}
