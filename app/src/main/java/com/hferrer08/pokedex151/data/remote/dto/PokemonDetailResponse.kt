package com.hferrer08.pokedex151.data.remote.dto

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: PokemonSpritesResponse,
    val types: List<PokemonTypeSlotResponse>
)

data class PokemonSpritesResponse(
    val front_default: String?
)

data class PokemonTypeSlotResponse(
    val slot: Int,
    val type: PokemonTypeResponse
)

data class PokemonTypeResponse(
    val name: String
)