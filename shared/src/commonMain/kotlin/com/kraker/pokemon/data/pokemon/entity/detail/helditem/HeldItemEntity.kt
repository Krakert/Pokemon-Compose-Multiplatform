package com.kraker.pokemon.data.pokemon.entity.detail.helditem

import kotlinx.serialization.Serializable

@Serializable
data class HeldItemEntity(
    val item: HeldItemInfoEntity?,
    val version_details: List<HeldItemVersionEntity?>?
)
