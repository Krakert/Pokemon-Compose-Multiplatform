package com.kraker.pokemon.domain.pokemon.model

data class PokemonOverview(
    val count: Int,
    val batch: Int,
    val results: List<PokemonOverviewItem>
)
