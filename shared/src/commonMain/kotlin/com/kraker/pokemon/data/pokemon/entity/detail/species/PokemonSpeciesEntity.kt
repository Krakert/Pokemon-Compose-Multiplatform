package com.kraker.pokemon.data.pokemon.entity.detail.species

import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesEntity(
    val name: String?,
    val url: String?
)
