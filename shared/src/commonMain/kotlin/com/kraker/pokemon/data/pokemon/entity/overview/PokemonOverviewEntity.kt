package com.kraker.pokemon.data.pokemon.entity.overview

import kotlinx.serialization.Serializable

@Serializable
data class PokemonOverviewEntity(
    val count: Int?,
    val next: String?,
    val results: List<PokemonOverviewItemEntity?>?
)
