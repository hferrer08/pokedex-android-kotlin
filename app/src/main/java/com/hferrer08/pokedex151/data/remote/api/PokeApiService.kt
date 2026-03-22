package com.hferrer08.pokedex151.data.remote.api

import com.hferrer08.pokedex151.data.remote.dto.PokemonDetailResponse
import com.hferrer08.pokedex151.data.remote.dto.PokemonListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonListResponseDto

    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int
    ): PokemonDetailResponse
}