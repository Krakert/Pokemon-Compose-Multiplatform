package com.kraker.pokemon.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kraker.pokemon.presentation.detail.components.TextRow
import com.kraker.pokemon.presentation.detail.model.PokemonDetailDisplay

@Composable
fun PokemonDetailAbout(pokemon: PokemonDetailDisplay) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            item { TextRow(header = pokemon.name.header, text = pokemon.name.text) }
            item { TextRow(header = pokemon.idWithLeadingZeros.header, text = pokemon.idWithLeadingZeros.text) }
            item { TextRow(header = pokemon.base.header, text = pokemon.base.text) }
            item { TextRow(header = pokemon.weight.header, text = pokemon.weight.text) }
            item { TextRow(header = pokemon.height.header, text = pokemon.height.text) }
            item { TextRow(header = pokemon.abilities.header, text = pokemon.types.text) }
            item { TextRow(header = pokemon.abilities.header, text = pokemon.abilities.text) }
        }
    }
}
