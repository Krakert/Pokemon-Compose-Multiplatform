package com.kraker.pokemon.pokemon.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kraker.pokemon.presentation.detail.components.ProgressBarRow
import com.kraker.pokemon.presentation.detail.model.PokemonDetailStatsDisplay

@Composable
fun PokemonDetailStats(stats: List<PokemonDetailStatsDisplay>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            stats.forEach {
                item {
                    ProgressBarRow(it)
                }
            }
        }
    }
}