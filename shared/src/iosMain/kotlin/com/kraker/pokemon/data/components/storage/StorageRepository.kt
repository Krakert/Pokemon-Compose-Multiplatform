package com.kraker.pokemon.data.components.storage

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import platform.Foundation.NSUserDefaults

actual class StorageRepository {

    companion object {
        const val favouriteField = "FAVOURITE_POKEMON"
        const val setName = "PokemonUserDefault"
    }

    private val userDefault: NSUserDefaults = NSUserDefaults(suiteName = setName)

    actual suspend fun getAll(): Result<List<Int>> {
        val result = userDefault.stringForKey(favouriteField).toString()

        return if (result.isEmpty()){
            Result.success(emptyList())
        } else{
            Result.runCatching { Json.decodeFromString(result) }
        }
    }

    actual fun addPokemon(value: Int) {
        val resultString = userDefault.stringForKey(favouriteField)
        if (resultString.isNullOrEmpty()) {
            userDefault.setObject(Json.encodeToString(arrayOf(value.toString())), favouriteField)
        } else {
            val resultObject = Json.decodeFromString<List<Int>>(resultString) as MutableList
            if (!resultObject.contains(value)) {
                resultObject.add(value)
                userDefault.setObject(Json.encodeToString(resultObject), favouriteField)
            }
        }
    }

    actual fun removePokemon(value: Int) {
        val resultString = userDefault.stringForKey(favouriteField)
        if (!resultString.isNullOrEmpty()) {
            val resultObject = Json.decodeFromString<List<Int>>(resultString.toString()) as MutableList
            if (resultObject.contains(value)) {
                resultObject.remove(value)
                userDefault.setObject(Json.encodeToString(resultObject), favouriteField)
            }
        }
    }

    actual fun isPresent(value: Int): Boolean {
        if (userDefault.stringForKey(favouriteField).isNullOrEmpty()) {
            return false
        } else {
            val resultObject = Json.decodeFromString<List<Int>>(userDefault.stringForKey(favouriteField).toString())
            if (resultObject.contains(value)) {
                return true
            }
        }
        return false
    }
}
