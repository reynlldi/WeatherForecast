package com.example.weatherforecast.screen.favorite

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.model.Favorite
import com.example.weatherforecast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: WeatherDbRepository
) : ViewModel() {
//    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
//    val favList = _favList.asStateFlow()
//
//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getAllFavorite().distinctUntilChanged()
//                .collectLatest { listOfFav ->
//                    if (listOfFav.isEmpty()){
//                        Log.d("TAG", ": Empty Fav")
//                    }else{
//                        _favList.value = listOfFav
//                        Log.d("FAV", ": ${favList.value}")
//                    }
//                }
//        }
//    }

    val getAllFavorite = repository.getAllFavorite()

    fun insertFavorite(favorite: Favorite){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavorite(favorite)
        }
    }

    fun updateFavorite(favorite: Favorite){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(favorite)
        }
    }

    fun deleteAllFavorite(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFavorite()
        }
    }

    fun getFavoriteById(city: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavoriteById(city)
        }
    }

    fun undoDeleteFavorites(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavorite(favorite)
        }
    }
}