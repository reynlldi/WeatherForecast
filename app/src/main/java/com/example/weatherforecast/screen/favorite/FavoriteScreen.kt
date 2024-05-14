package com.example.weatherforecast.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecast.model.Favorite
import com.example.weatherforecast.navigation.Screen
import com.example.weatherforecast.widget.WeatherAppBar
import com.example.weatherforecast.widget.mySnackBar

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            WeatherAppBar(
                title = "Favorite",
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                navController = navController
            ) {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val list =
                    favoriteViewModel.getAllFavorite.collectAsState(initial = emptyList()).value
                if (list.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Kosong nih!"
                        )
                    }
                } else {
                    LazyColumn {
                        items(items = list) { favorite ->
                            CityRow(
                                favorite = favorite,
                                navController = navController,
                                favoriteViewModel
                            ) {
                                favoriteViewModel.deleteFavorite(favorite)
                                mySnackBar(
                                    scope = scope,
                                    snackBarHostState = snackBarHostState,
                                    actionLabel = "Ga Jadi Hapus",
                                    message = "Berhasil dihapus!",
                                    onAction = {
                                        favoriteViewModel.undoDeleteFavorites(favorite)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CityRow(
    favorite: Favorite,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel,
    onClicked: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(Screen.MainScreen.name + "/${favorite.city}")
            },
        shape = CircleShape,
        shadowElevation = 2.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = favorite.city, modifier = Modifier.padding(start = 8.dp))
            Text(text = favorite.country, modifier = Modifier.padding(8.dp))
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                modifier = Modifier.clickable {
                    onClicked.invoke()
                },
                tint = Color.Red.copy(alpha = 0.3f)
            )
        }
    }
}