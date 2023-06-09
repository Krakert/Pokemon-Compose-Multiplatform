package com.kraker.pokemon.domain.pokemon

import com.kraker.pokemon.data.components.storage.StorageRepository


class AddFavouritePokemon internal constructor(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(id: Int) {
        return storageRepository.addPokemon(id)
    }
}
