package com.kraker.pokemon.domain.pokemon

import com.kraker.pokemon.domain.pokemon.model.PokemonDetail

class GetPokemonDetail internal constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(id: Int): Result<PokemonDetail> {
        return repository.getDetails(id)
    }
}
