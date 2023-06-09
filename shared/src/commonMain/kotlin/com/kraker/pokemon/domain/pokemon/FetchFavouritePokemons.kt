package com.kraker.pokemon.domain.pokemon

import com.kraker.pokemon.domain.pokemon.model.PokemonOverviewItem

class FetchFavouritePokemons internal constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(ids: List<Int>): Result<List<PokemonOverviewItem>> {
        return repository.getFavouritePokemons(ids)
    }
}
