package com.kraker.pokemon.domain.pokemon

import com.kraker.pokemon.data.components.storage.StorageRepository

class GetFavouriteStorage internal constructor(
    private val storageRepository: StorageRepository
) {
    suspend operator fun invoke(): Result<List<Int>> = storageRepository.getAll()
}
