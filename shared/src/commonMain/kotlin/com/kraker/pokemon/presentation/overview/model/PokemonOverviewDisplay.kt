package com.kraker.pokemon.presentation.overview.model

data class PokemonOverviewDisplay(
    var selectedContent: PokemonOverviewContent,
    var pokemons: List<PokemonOverviewItemDisplay>
)
