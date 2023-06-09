package com.kraker.pokemon.domain.pokemon

import com.kraker.pokemon.domain.pokemon.model.PokemonOverview

class GetPokemonOverview internal constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(batch: Int): Result<PokemonOverview> {
        return repository.getOverview(batch)
    }
}
