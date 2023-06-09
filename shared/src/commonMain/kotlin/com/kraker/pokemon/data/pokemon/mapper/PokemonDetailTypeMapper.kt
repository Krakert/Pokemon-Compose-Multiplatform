package com.kraker.pokemon.data.pokemon.mapper

import com.kraker.pokemon.data.extension.requireNotNull
import com.kraker.pokemon.data.pokemon.entity.detail.pasttype.PokemonTypeEntity
class PokemonDetailTypeMapper {
    fun map(entity: PokemonTypeEntity): String { return entity.type?.name.requireNotNull() }
}
