package com.kraker.pokemon.data.pokemon.entity.detail.pasttype

import kotlinx.serialization.Serializable

@Serializable
data class PastTypeEntity(
    val generation: PastTypeGenerationEntity?,
    val types: List<PokemonTypeEntity?>?
)
