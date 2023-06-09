package com.kraker.pokemon.data.pokemon.mapper

class PokemonImageMapper {

    companion object {
        private const val SPRITE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$.png"
    }

    fun getSpriteURL(id: Int): String = SPRITE_URL.replace("$", id.toString())
}
