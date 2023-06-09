package com.kraker.pokemon.domain.pokemon.model

data class PokemonDetail(
    val name: String,
    val id: Int,
    val baseExperience: Int,
    val height: Int,
    val weight: Int,
    val types: List<String>,
    val abilities: List<String>,
    val stats: List<PokemonStat>,
    val imageUrl: String,
    val evolutions: PokemonEvolutionChain?
)
