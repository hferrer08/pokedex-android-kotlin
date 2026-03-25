package com.hferrer08.pokedex151.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hferrer08.pokedex151.data.local.FavoritesManager
import com.hferrer08.pokedex151.data.repository.PokemonRepository
import com.hferrer08.pokedex151.data.mapper.toDomain
import com.hferrer08.pokedex151.domain.model.Pokemon
import kotlinx.coroutines.launch


class PokemonFavoritesViewModel(
    private val repository: PokemonRepository = PokemonRepository()
) : ViewModel() {

    var favoritePokemonList: List<Pokemon> = emptyList()
        private set

    var isLoading: Boolean = false
        private set

    var errorMessage: String? = null
        private set

    fun loadFavorites(favoritesManager: FavoritesManager) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val favoriteIds = favoritesManager
                    .getFavorites()
                    .mapNotNull { it.toIntOrNull() }
                    .sorted()

                val pokemonList = favoriteIds.map { id ->
                    repository.getPokemonById(id).toDomain()
                }

                favoritePokemonList = pokemonList
            } catch (e: Exception) {
                errorMessage = e.message ?: "Error al cargar favoritos"
            } finally {
                isLoading = false
            }
        }
    }
}