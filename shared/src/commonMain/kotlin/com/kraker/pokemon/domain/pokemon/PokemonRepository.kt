package com.kraker.pokemon.domain.pokemon

import com.kraker.pokemon.domain.pokemon.model.PokemonDetail
import com.kraker.pokemon.domain.pokemon.model.PokemonOverview
import com.kraker.pokemon.domain.pokemon.model.PokemonOverviewItem

interface PokemonRepository {
    suspend fun getOverview(batch: Int): Result<PokemonOverview>

    suspend fun getDetails(id: Int): Result<PokemonDetail>

    suspend fun getFavouritePokemons(ids: List<Int>): Result<List<PokemonOverviewItem>>
}
