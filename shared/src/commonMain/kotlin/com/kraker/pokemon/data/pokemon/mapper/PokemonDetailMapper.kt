package com.kraker.pokemon.data.pokemon.mapper

import com.kraker.pokemon.data.extension.requireNotNull
import com.kraker.pokemon.data.pokemon.entity.detail.PokemonEntity
import com.kraker.pokemon.data.pokemon.entity.detail.evolution.PokemonEvolutionChainEntity
import com.kraker.pokemon.domain.pokemon.model.PokemonDetail

class PokemonDetailMapper(
    private val pokemonDetailTypeMapper: PokemonDetailTypeMapper,
    private val pokemonDetailAbilitiesMapper: PokemonDetailAbilitiesMapper,
    private val pokemonStatsMapper: PokemonDetailStatsMapper,
    private val evolutionChainMapper: PokemonDetailEvolutionChainMapper
) {

    fun map(entity: PokemonEntity, evolutionChainEntity: PokemonEvolutionChainEntity?): PokemonDetail {
        return PokemonDetail(
            name = entity.name.requireNotNull(),
            id = entity.id.requireNotNull(),
            baseExperience = entity.base_experience.requireNotNull(),
            height = entity.height.requireNotNull(),
            weight = entity.weight.requireNotNull(),
            types = entity.types?.mapNotNull { item -> item?.let { pokemonDetailTypeMapper.map(it) } }.requireNotNull(),
            abilities = entity.abilities?.mapNotNull { item -> item?.let { pokemonDetailAbilitiesMapper.map(it) } }.requireNotNull(),
            imageUrl = entity.sprites?.other?.official_artwork?.front_default.requireNotNull(),
            stats = entity.stats?.mapNotNull { item -> item?.let { pokemonStatsMapper.map(it) } }.requireNotNull(),
            evolutions = evolutionChainMapper.map(evolutionChainEntity)
        )
    }
}
