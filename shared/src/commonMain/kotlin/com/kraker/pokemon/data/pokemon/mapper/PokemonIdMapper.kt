package com.kraker.pokemon.data.pokemon.mapper

class PokemonIdMapper {
    companion object {
        private val ID_REGEX = "(?<=/)\\d+(?=/)".toRegex()
    }

    fun findId(url: String) = ID_REGEX.findAll(url).last().value.toInt()
}
