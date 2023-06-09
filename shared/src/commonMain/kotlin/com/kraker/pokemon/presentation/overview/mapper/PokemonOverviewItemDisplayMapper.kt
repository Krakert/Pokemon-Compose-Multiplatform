package com.kraker.pokemon.presentation.overview.mapper

import com.kraker.pokemon.domain.pokemon.model.PokemonOverviewItem
import com.kraker.pokemon.presentation.formatter.IdFormatter
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewItemDisplay

class PokemonOverviewItemDisplayMapper(
    private val idFormatter: IdFormatter
) {
    fun map(item: PokemonOverviewItem): PokemonOverviewItemDisplay {
        with(item) {
            return PokemonOverviewItemDisplay(
                id = id,
                name = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                idWithLeadingZeros = idFormatter.intToStringWithLeadingZeros(id),
                imageUrl = image
            )
        }
    }
}
