package com.hferrer08.pokedex151.data.mapper

import com.hferrer08.pokedex151.data.remote.dto.PokemonResultDto
import com.hferrer08.pokedex151.domain.model.Pokemon
import com.hferrer08.pokedex151.data.remote.dto.PokemonDetailResponse

fun PokemonResultDto.toDomain(): Pokemon {
    val id = url
        .trimEnd('/')
        .split("/")
        .last()
        .toInt()

    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"


    return Pokemon(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}

fun PokemonDetailResponse.toDomain(): Pokemon {
    val imageUrl =
        sprites.front_default
            ?: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

    return Pokemon(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}