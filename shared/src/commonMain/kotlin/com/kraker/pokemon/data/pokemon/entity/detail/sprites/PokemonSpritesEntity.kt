package com.kraker.pokemon.data.pokemon.entity.detail.sprites

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpritesEntity(
    val back_default: String?,
    val front_default: String?,
    val other: OtherArtworkEntity?
)

@Serializable
data class OtherArtworkEntity(
    @SerialName("official-artwork") val official_artwork: OfficialArtworkEntity?
)

@Serializable
data class OfficialArtworkEntity(
    val front_default: String?,
    val front_shiny: String?
)
