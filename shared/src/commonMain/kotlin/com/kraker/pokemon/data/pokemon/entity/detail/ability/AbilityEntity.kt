package com.kraker.pokemon.data.pokemon.entity.detail.ability

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityEntity(
    val ability: AbilityInfoEntity?,
    @SerialName("is_hidden")
    val isHidden: Boolean?,
    val slot: Int?
)
