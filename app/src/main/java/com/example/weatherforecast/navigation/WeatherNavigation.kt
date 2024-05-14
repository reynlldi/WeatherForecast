package com.example.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherforecast.screen.about.AboutScreen
import com.example.weatherforecast.screen.favorite.FavoriteScreen
import com.example.weatherforecast.screen.main.MainScreen
import com.example.weatherforecast.screen.main.MainViewModel
import com.example.weatherforecast.screen.search.SearchScreen
import com.example.weatherforecast.screen.setting.SettingScreen
import com.example.weatherforecast.screen.splash.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.name) {
        composable(Screen.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(
            Screen.MainScreen.name + "/{city}",
            arguments = listOf(navArgument("city") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel, city = city)
            }
        }

        composable(Screen.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        composable(Screen.AboutScreen.name){
            AboutScreen(navController = navController)
        }

        composable(Screen.FavoriteScreen.name){
            FavoriteScreen(navController = navController)
        }

        composable(Screen.SettingsScreen.name){
            SettingScreen(navController = navController)
        }
    }
}