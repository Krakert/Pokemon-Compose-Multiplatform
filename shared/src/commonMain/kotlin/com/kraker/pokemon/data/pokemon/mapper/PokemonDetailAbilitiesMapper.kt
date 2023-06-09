package com.kraker.pokemon.data.pokemon.mapper

import com.kraker.pokemon.data.extension.requireNotNull
import com.kraker.pokemon.data.pokemon.entity.detail.ability.AbilityEntity
class PokemonDetailAbilitiesMapper {
    fun map(entity: AbilityEntity): String {
        return entity.ability?.name.requireNotNull()
    }
}
