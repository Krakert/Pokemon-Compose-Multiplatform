package com.kraker.pokemon.data.components.storage

expect class StorageRepository {

    suspend fun getAll(): Result<List<Int>>

    fun addPokemon(value: Int)

    fun removePokemon(value: Int)

    fun isPresent(value: Int): Boolean
}
