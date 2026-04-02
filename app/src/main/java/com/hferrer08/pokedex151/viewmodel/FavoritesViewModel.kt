package com.hferrer08.pokedex151.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.hferrer08.pokedex151.data.local.FavoritesManager

class FavoritesViewModel(
    private val favoritesManager: FavoritesManager
) : ViewModel() {

    var favoriteIds by mutableStateOf<Set<Int>>(emptySet())
        private set

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        favoriteIds = favoritesManager
            .getFavorites()
            .mapNotNull { it.toIntOrNull() }
            .toSet()
    }

    fun isFavorite(pokemonId: Int): Boolean {
        return pokemonId in favoriteIds
    }

    fun toggleFavorite(pokemonId: Int) {
        favoritesManager.toggleFavorite(pokemonId)
        loadFavorites()
    }
}