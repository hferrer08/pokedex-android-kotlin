package com.hferrer08.pokedex151.data.repository

import com.hferrer08.pokedex151.data.mapper.toDomain
import com.hferrer08.pokedex151.data.remote.api.RetrofitInstance
import com.hferrer08.pokedex151.domain.model.Pokemon

class PokemonRepository {

    suspend fun getPokemonList(): List<Pokemon> {
        val response = RetrofitInstance.api.getPokemonList()
        return response.results.map { it.toDomain() }
    }
}