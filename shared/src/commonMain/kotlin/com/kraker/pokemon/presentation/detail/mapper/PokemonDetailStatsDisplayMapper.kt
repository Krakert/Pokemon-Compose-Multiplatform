package com.kraker.pokemon.presentation.detail.mapper

import com.kraker.pokemon.domain.pokemon.model.PokemonStat
import com.kraker.pokemon.presentation.detail.model.PokemonDetailStatsDisplay
import com.kraker.pokemon.presentation.extension.kebabCaseTo

class PokemonDetailStatsDisplayMapper {
    fun map(stats: PokemonStat): PokemonDetailStatsDisplay {
        return PokemonDetailStatsDisplay(
            name = stats.name.kebabCaseTo(),
            baseNumber = stats.baseNumber,
            progress = stats.baseNumber / 255.0f
        )
    }
}
