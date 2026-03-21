package com.hferrer08.pokedex151.domain.model

import retrofit2.http.Url

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String
)