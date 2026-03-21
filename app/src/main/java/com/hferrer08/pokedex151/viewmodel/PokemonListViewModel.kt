package com.hferrer08.pokedex151.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hferrer08.pokedex151.data.repository.PokemonRepository
import com.hferrer08.pokedex151.domain.model.Pokemon
import kotlinx.coroutines.launch
import kotlin.math.ceil

class PokemonListViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private val pageSize = 20

    var pokemonList by mutableStateOf<List<Pokemon>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var currentPage by mutableIntStateOf(1)
        private set

    var totalPages by mutableIntStateOf(1)
        private set

    init {
        loadPokemonPage(1)
    }

    fun loadPokemonPage(page: Int) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val offset = (page - 1) * pageSize

                val (pokemon, totalCount) = repository.getPokemonPage(
                    limit = pageSize,
                    offset = offset
                )

                pokemonList = pokemon
                currentPage = page
                totalPages = ceil(totalCount / pageSize.toDouble()).toInt()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Ocurrió un error al cargar los Pokémon"
            } finally {
                isLoading = false
            }
        }
    }

    fun nextPage() {
        if (currentPage < totalPages) {
            loadPokemonPage(currentPage + 1)
        }
    }

    fun previousPage() {
        if (currentPage > 1) {
            loadPokemonPage(currentPage - 1)
        }
    }
}