package com.kraker.pokemon.presentation.detail.mapper

import com.kraker.pokemon.domain.pokemon.model.PokemonEvolutionChain
import com.kraker.pokemon.presentation.detail.model.PokemonDetailEvolutionDisplay
import com.kraker.pokemon.presentation.formatter.IdFormatter

class PokemonDetailEvolutionChainDisplayMapper(
    private val idFormatter: IdFormatter
) {
    fun map(pokemonEvolutionChain: PokemonEvolutionChain?): List<PokemonDetailEvolutionDisplay> {
        val list = mutableListOf<PokemonDetailEvolutionDisplay>()
        pokemonEvolutionChain?.evolutions?.map {
            list.add(
                PokemonDetailEvolutionDisplay(
                    name = it.name.replaceFirstChar { name -> if (name.isLowerCase()) name.titlecase() else name.toString() },
                    id = idFormatter.intToStringWithLeadingZeros(it.id),
                    imageUrl = it.imageUrl
                )
            )
        }
        return list
    }
}
