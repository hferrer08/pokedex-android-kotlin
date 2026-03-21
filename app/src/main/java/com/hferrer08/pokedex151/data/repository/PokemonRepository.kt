package com.hferrer08.pokedex151.data.repository

import com.hferrer08.pokedex151.data.mapper.toDomain
import com.hferrer08.pokedex151.data.remote.api.RetrofitInstance
import com.hferrer08.pokedex151.data.remote.dto.PokemonListResponseDto
import com.hferrer08.pokedex151.domain.model.Pokemon

class PokemonRepository {

    suspend fun getPokemonPage(limit: Int, offset: Int): Pair<List<Pokemon>, Int> {
        val response: PokemonListResponseDto =
            RetrofitInstance.api.getPokemonList(limit = limit, offset = offset)

        val pokemonList = response.results.map { it.toDomain() }

        return Pair(pokemonList, response.count)
    }
}