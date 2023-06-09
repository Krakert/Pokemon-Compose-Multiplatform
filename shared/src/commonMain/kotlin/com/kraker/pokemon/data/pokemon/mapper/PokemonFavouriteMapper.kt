package com.kraker.pokemon.data.pokemon.mapper

import com.kraker.pokemon.data.extension.requireNotNull
import com.kraker.pokemon.data.pokemon.entity.overview.PokemonOverviewItemEntity
import com.kraker.pokemon.domain.pokemon.model.PokemonOverviewItem
class PokemonFavouriteMapper(
    private val imageMapper: PokemonImageMapper,
    private val idMapper: PokemonIdMapper
) {

    fun map(entity: PokemonOverviewItemEntity?): PokemonOverviewItem {
        val id = idMapper.findId(entity?.url.requireNotNull())
        return PokemonOverviewItem(
            id = id,
            name = entity?.name.requireNotNull(),
            image = imageMapper.getSpriteURL(id)
        )
    }
}
