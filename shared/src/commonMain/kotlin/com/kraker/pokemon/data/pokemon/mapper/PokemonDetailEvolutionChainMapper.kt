package com.kraker.pokemon.data.pokemon.mapper

import com.kraker.pokemon.data.extension.requireNotNull
import com.kraker.pokemon.data.pokemon.entity.detail.evolution.PokemonEvolutionChainEntity
import com.kraker.pokemon.data.pokemon.entity.detail.evolution.PokemonEvolutionSpeciesEntity
import com.kraker.pokemon.data.pokemon.entity.detail.evolution.PokemonEvolvesToEntity
import com.kraker.pokemon.domain.pokemon.model.PokemonEvolution
import com.kraker.pokemon.domain.pokemon.model.PokemonEvolutionChain

class PokemonDetailEvolutionChainMapper(
    private val imageMapper: PokemonImageMapper,
    private val idMapper: PokemonIdMapper
) {
    fun map(entity: PokemonEvolutionChainEntity?): PokemonEvolutionChain? = entity?.let {
        val list = mutableListOf<PokemonEvolution>()
        val id = idMapper.findId(entity.chain?.species?.url.toString())
        list.add(mapEvolution(id, entity.chain?.species))

        entity.chain?.evolves_to?.forEach {
            list.addAll(mapEvolutions(it))
        }
        return PokemonEvolutionChain(list)
    }

    private fun mapEvolutions(evolvesTo: PokemonEvolvesToEntity?): MutableList<PokemonEvolution> {
        val list = mutableListOf<PokemonEvolution>()

        evolvesTo?.evolves_to?.let { listEvolves ->
            listEvolves.forEach { evolvesEntity ->
                list.addAll(mapEvolutions(evolvesEntity))
            }
            val id = idMapper.findId(evolvesTo.species?.url.toString())
            list.add(mapEvolution(id, evolvesTo.species))
        }
        list.sortBy { it.id }
        return list
    }

    private fun mapEvolution(id: Int, species: PokemonEvolutionSpeciesEntity?) = PokemonEvolution(
        name = species?.name.requireNotNull(),
        id = id,
        imageUrl = imageMapper.getSpriteURL(id)
    )
}
