package com.kraker.pokemon.presentation.detail.mapper

import com.kraker.pokemon.presentation.detail.model.PokemonDetailAboutDisplay

class PokemonDetailAboutDisplayMapper {
    fun map(header: String, text: String) = PokemonDetailAboutDisplay(
        header = header,
        text = text
    )
}
