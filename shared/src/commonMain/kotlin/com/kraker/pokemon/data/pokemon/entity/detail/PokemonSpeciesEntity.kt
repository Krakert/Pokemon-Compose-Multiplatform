package com.kraker.pokemon.data.pokemon.entity.detail

import com.kraker.pokemon.data.pokemon.entity.detail.evolution.PokemonEvolutionChainUrlEntity
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesEntity(
    val evolution_chain: PokemonEvolutionChainUrlEntity?
)
