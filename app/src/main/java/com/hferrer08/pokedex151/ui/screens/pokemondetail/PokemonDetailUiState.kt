package com.hferrer08.pokedex151.ui.screens.pokemondetail

import com.hferrer08.pokedex151.data.remote.dto.PokemonDetailResponse

data class PokemonDetailUiState(
    val isLoading: Boolean = false,
    val pokemon: PokemonDetailResponse? = null,
    val error: String? = null
)