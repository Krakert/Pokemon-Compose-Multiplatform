package com.kraker.pokemon.data.pokemon.mapper

import com.kraker.pokemon.data.extension.requireNotNull
import com.kraker.pokemon.data.pokemon.entity.overview.PokemonOverviewEntity
import com.kraker.pokemon.domain.pokemon.model.PokemonOverview
class PokemonOverviewMapper(
    private val pokemonOverviewItemMapper: PokemonOverviewItemMapper
) {

    fun map(entity: PokemonOverviewEntity, batch: Int): PokemonOverview {
        return PokemonOverview(
            count = entity.count ?: 0,
            batch = batch,
            results = entity.results?.mapNotNull { item -> item?.let { pokemonOverviewItemMapper.map(it) } }.requireNotNull()
        )
    }
}
