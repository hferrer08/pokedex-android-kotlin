package com.hferrer08.pokedex151.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hferrer08.pokedex151.data.repository.PokemonRepository
import com.hferrer08.pokedex151.domain.model.Pokemon
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class PokemonListViewModel : ViewModel() {

    private val repository = PokemonRepository()

    var pokemonList by mutableStateOf<List<Pokemon>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        loadPokemon()
    }

    private fun loadPokemon() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                pokemonList = repository.getPokemonList()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Ocurrió un error al cargar los Pokémon"
            } finally {
                isLoading = false
            }
        }
    }
}