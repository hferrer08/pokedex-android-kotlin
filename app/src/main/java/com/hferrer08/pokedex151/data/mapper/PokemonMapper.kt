package com.hferrer08.pokedex151.data.mapper

import com.hferrer08.pokedex151.data.remote.dto.PokemonResultDto
import com.hferrer08.pokedex151.domain.model.Pokemon

fun PokemonResultDto.toDomain(): Pokemon {
    return Pokemon(
        name = this.name,
        url = this.url
    )
}