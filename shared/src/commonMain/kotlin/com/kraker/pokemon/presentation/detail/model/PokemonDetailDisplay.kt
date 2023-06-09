package com.kraker.pokemon.presentation.detail.model

data class PokemonDetailDisplay(
    val name: PokemonDetailAboutDisplay,
    val id: Int,
    val idWithLeadingZeros: PokemonDetailAboutDisplay,
    val badges: List<PokemonDetailBadgeDisplay>,
    val base: PokemonDetailAboutDisplay,
    val weight: PokemonDetailAboutDisplay,
    val height: PokemonDetailAboutDisplay,
    val types: PokemonDetailAboutDisplay,
    val abilities: PokemonDetailAboutDisplay,
    val stats: List<PokemonDetailStatsDisplay>,
    val imageUrl: String,
    val evolutions: List<PokemonDetailEvolutionDisplay>?,
    var isFavourite: Boolean
)
