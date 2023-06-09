package com.kraker.pokemon.data.components.storage

import android.content.Context
import android.content.SharedPreferences
import com.kraker.pokemon.data.components.context.ContextWrapper
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

actual class StorageRepository(
    context: ContextWrapper
) {

    companion object {
        const val favouriteField = "FAVOURITE_POKEMON"
        const val setName: String = "PokemonSharedPreferences"
    }

    private var preferences: SharedPreferences = context.get().getSharedPreferences(
        setName,
        Context.MODE_PRIVATE
    )

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val edit = edit()
        operation(edit)
        edit.apply()
    }

    actual suspend fun getAll(): Result<List<Int>> {
        val result = preferences.favourite
        return if (result.isEmpty()){
            Result.success(emptyList())
        } else{
            Result.runCatching { Json.decodeFromString(result) }
        }
    }

    private var SharedPreferences.favourite
        get() = getString(favouriteField, "").toString()
        set(value) {
            edit {
                it.putString(favouriteField, value)
            }
        }

    actual fun addPokemon(value: Int) {
        val resultString = preferences.favourite
        if (resultString.isEmpty()) {
            preferences.favourite = Json.encodeToString(arrayOf(value.toString().lowercase()))
        } else {
            val resultObject = Json.decodeFromString<List<Int>>(resultString) as MutableList
            if (!resultObject.contains(value)) {
                resultObject.add(value)
                preferences.favourite = Json.encodeToString(resultObject)
            }
        }
    }

    actual fun removePokemon(value: Int) {
        val resultString = preferences.favourite
        if (resultString.isNotEmpty()) {
            val resultObject = Json.decodeFromString<List<Int>>(resultString) as MutableList
            if (resultObject.contains(value)) {
                resultObject.remove(value)
                preferences.favourite = Json.encodeToString(resultObject)
            }
        }
    }

    actual fun isPresent(value: Int): Boolean {
        if (preferences.favourite.isEmpty()) {
            return false
        } else {
            val resultObject = Json.decodeFromString<List<Int>>(preferences.favourite)
            if (resultObject.contains(value)) {
                return true
            }
        }
        return false
    }
}
