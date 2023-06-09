package com.kraker.pokemon.data.pokemon.entity.detail.move

import kotlinx.serialization.Serializable

@Serializable
data class MoveEntity(
    val move: MoveInfoEntity?,
    val version_group_details: List<VersionGroupDetailsEntity?>?
)
