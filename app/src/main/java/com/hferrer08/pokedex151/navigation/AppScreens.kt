package com.hferrer08.pokedex151.navigation

sealed class AppScreens(val route: String) {
    data object PokemonList : AppScreens("pokemon_list")
    data object PokemonDetail : AppScreens("pokemon_detail/{pokemonId}") {
        fun createRoute(pokemonId: Int): String = "pokemon_detail/$pokemonId"
    }
}