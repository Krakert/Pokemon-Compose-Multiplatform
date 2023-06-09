package com.kraker.pokemon.data.pokemon.entity.overview

import kotlinx.serialization.Serializable

@Serializable
data class PokemonOverviewItemEntity(
    val name: String?,
    val url: String?
)
