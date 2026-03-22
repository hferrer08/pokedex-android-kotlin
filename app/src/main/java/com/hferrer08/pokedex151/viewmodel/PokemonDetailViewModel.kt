package com.hferrer08.pokedex151.ui.screens.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hferrer08.pokedex151.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonDetailUiState())
    val uiState: StateFlow<PokemonDetailUiState> = _uiState.asStateFlow()

    fun loadPokemon(id: Int) {
        viewModelScope.launch {
            _uiState.value = PokemonDetailUiState(isLoading = true)

            try {
                val pokemon = repository.getPokemonById(id)
                _uiState.value = PokemonDetailUiState(
                    isLoading = false,
                    pokemon = pokemon
                )
            } catch (e: Exception) {
                _uiState.value = PokemonDetailUiState(
                    isLoading = false,
                    error = e.message ?: "Ocurrió un error al cargar el Pokémon"
                )
            }
        }
    }
}

class PokemonDetailViewModelFactory(
    private val repository: PokemonRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonDetailViewModel(repository) as T
    }
}