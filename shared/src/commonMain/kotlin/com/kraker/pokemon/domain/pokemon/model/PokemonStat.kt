package com.kraker.pokemon.domain.pokemon.model

data class PokemonStat(
    val name: String,
    val baseNumber: Int,
    val max: Int = 255
)
