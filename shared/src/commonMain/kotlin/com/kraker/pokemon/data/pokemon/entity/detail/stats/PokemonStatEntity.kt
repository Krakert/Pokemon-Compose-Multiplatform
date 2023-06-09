package com.kraker.pokemon.data.pokemon.entity.detail.stats

import kotlinx.serialization.Serializable

@Serializable
data class PokemonStatEntity(
    val base_stat: Int?,
    val effort: Int?,
    val stat: PokemonStatInfoEntity?
)
