package com.kraker.pokemon.data.pokemon.entity.detail.gameindex

import kotlinx.serialization.Serializable

@Serializable
data class GameIndexVersionEntity(
    val name: String?,
    val url: String?
)
