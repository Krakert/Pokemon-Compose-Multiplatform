package com.kraker.pokemon.data.pokemon.entity.detail.helditem

import kotlinx.serialization.Serializable

@Serializable
data class HeldItemVersionEntity(
    val version: HeldItemVersionInfoEntity?,
    val rarity: Int?
)
