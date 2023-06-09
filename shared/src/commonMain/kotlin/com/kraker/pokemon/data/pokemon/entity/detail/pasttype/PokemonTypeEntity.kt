package com.kraker.pokemon.data.pokemon.entity.detail.pasttype

import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeEntity(
    val slot: Int?,
    val type: PokemonTypeInfoEntity?
)
