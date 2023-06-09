package com.kraker.pokemon.data.pokemon.entity.detail.evolution

import kotlinx.serialization.Serializable

@Serializable
data class PokemonEvolvesToEntity(
    val evolves_to: List<PokemonEvolvesToEntity>?,
    val is_baby: Boolean?,
    val species: PokemonEvolutionSpeciesEntity?
)
