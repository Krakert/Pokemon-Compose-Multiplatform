package com.kraker.pokemon.data.pokemon.entity.detail.evolution

import kotlinx.serialization.Serializable

@Serializable
data class PokemonEvolutionChainItemEntity(
    val evolves_to: List<PokemonEvolvesToEntity?>?,
    val species: PokemonEvolutionSpeciesEntity?
)
