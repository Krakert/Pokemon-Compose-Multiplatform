package com.kraker.pokemon.data.pokemon.entity.detail.evolution

import kotlinx.serialization.Serializable

@Serializable
data class PokemonEvolutionChainEntity(
    val chain: PokemonEvolutionChainItemEntity?,
    val id: Int?
)
