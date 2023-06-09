package com.kraker.pokemon.data.pokemon.entity.detail.gameindex

import kotlinx.serialization.Serializable

@Serializable
data class GameIndexEntity(
    val game_index: Int?,
    val version: GameIndexVersionEntity?
)
