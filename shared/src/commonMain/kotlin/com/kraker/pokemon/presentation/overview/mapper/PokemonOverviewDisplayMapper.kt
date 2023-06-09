package com.kraker.pokemon.presentation.overview.mapper

import com.kraker.pokemon.domain.pokemon.model.PokemonOverview
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewContent
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewDisplay

class PokemonOverviewDisplayMapper(
    private val itemDisplayMapper: PokemonOverviewItemDisplayMapper
) {
    fun map(overview: PokemonOverview, content: PokemonOverviewContent): PokemonOverviewDisplay {
        return PokemonOverviewDisplay(
            selectedContent = content,
            pokemons = overview.results.map(itemDisplayMapper::map)
        )
    }
}
