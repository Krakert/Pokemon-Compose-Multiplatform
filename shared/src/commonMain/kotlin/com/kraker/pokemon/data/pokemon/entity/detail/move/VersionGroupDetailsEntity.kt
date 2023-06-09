package com.kraker.pokemon.data.pokemon.entity.detail.move

import kotlinx.serialization.Serializable

@Serializable
data class VersionGroupDetailsEntity(
    val level_learned_at: Int?,
    val move_learn_method: MoveLearnMethodEntity?,
    val version_group: VersionGroupEntity?
)
