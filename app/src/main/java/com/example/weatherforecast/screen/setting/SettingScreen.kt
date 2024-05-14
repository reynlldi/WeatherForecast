package com.example.weatherforecast.screen.setting

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.weatherforecast.widget.WeatherAppBar

@Composable
fun SettingScreen(navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Pengaturan",
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                navController = navController
            ){
                navController.popBackStack()
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}