package com.kraker.pokemon.data.pokemon.mapper

import com.kraker.pokemon.data.extension.requireNotNull
import com.kraker.pokemon.data.pokemon.entity.detail.stats.PokemonStatEntity
import com.kraker.pokemon.domain.pokemon.model.PokemonStat
class PokemonDetailStatsMapper {
    fun map(entity: PokemonStatEntity): PokemonStat {
        with(entity) {
            return PokemonStat(
                name = this.stat?.name.requireNotNull(),
                baseNumber = this.base_stat.requireNotNull()
            )
        }
    }
}
