package com.kraker.pokemon.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kraker.pokemon.MR
import com.kraker.pokemon.pokemon.presentation.components.CenterElement
import com.kraker.pokemon.presentation.detail.components.CardEvolution
import com.kraker.pokemon.presentation.detail.components.DashedVerticalLine
import com.kraker.pokemon.presentation.detail.model.PokemonDetailEvolutionDisplay
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun PokemonDetailEvolutions(evolutions: List<PokemonDetailEvolutionDisplay>?) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (evolutions?.isNotEmpty() == true) {
            LazyColumn {
                evolutions.forEachIndexed { index, pokemonDetailEvolutionDisplay ->
                    item {
                        when (index) {
                            evolutions.lastIndex -> {
                                CardEvolution(evolution = pokemonDetailEvolutionDisplay)
                            }
                            else -> {
                                CardEvolution(evolution = pokemonDetailEvolutionDisplay)
                                DashedVerticalLine()
                            }
                        }
                    }
                }
            }
        } else {
            CenterElement {
                Text(text = stringResource(MR.strings.details_unable_to_load))
            }
        }
    }
}
