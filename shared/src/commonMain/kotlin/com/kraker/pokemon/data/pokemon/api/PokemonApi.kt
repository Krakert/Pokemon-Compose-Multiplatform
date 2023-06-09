package com.kraker.pokemon.data.pokemon.api

import com.kraker.pokemon.data.components.net.models.ApiMethod
import com.kraker.pokemon.data.components.net.models.ApiRequest
import com.kraker.pokemon.data.components.net.models.Query
import com.kraker.pokemon.data.pokemon.entity.detail.PokemonEntity
import com.kraker.pokemon.data.pokemon.entity.detail.PokemonSpeciesEntity
import com.kraker.pokemon.data.pokemon.entity.detail.evolution.PokemonEvolutionChainEntity
import com.kraker.pokemon.data.pokemon.entity.overview.PokemonOverviewEntity

internal object PokemonApi {

    fun getAllPokemon(limit: Int, offset: Int) = ApiRequest<PokemonOverviewEntity>(
        ApiMethod.GET,
        path = "pokemon",
        parameters = listOf(
            Query(
                "limit",
                limit.toString()
            ),
            Query(
                "offset",
                offset.toString()
            )
        )
    )

    fun getDetails(pokemonId: Int) = ApiRequest<PokemonEntity>(
        ApiMethod.GET,
        path = "pokemon/$pokemonId"
    )

    fun getSpecies(pokemonId: Int) = ApiRequest<PokemonSpeciesEntity>(
        ApiMethod.GET,
        path = "pokemon-species/$pokemonId"
    )

    fun getEvolutions(chainUrl: String) = ApiRequest<PokemonEvolutionChainEntity>(
        ApiMethod.GET,
        path = chainUrl
    )

    fun getPokemon(id: Int) = ApiRequest<PokemonOverviewEntity>(
        ApiMethod.GET,
        path = "pokemon",
        parameters = listOf(
            Query("limit", "1"),
            Query("offset", (id - 1).toString())
        )
    )
}
