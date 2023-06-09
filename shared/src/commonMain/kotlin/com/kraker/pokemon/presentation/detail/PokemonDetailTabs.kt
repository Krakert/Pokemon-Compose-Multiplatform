package com.kraker.pokemon.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kraker.pokemon.MR.colors.PrimaryColor
import com.kraker.pokemon.MR.colors.TextPrimaryColor
import com.kraker.pokemon.MR.colors.TextSecondaryColor
import com.kraker.pokemon.pokemon.detail.PokemonDetailStats
import com.kraker.pokemon.presentation.detail.model.PokemonDetailDisplay
import com.kraker.pokemon.presentation.detail.model.PokemonDetailTab
import com.kraker.pokemon.presentation.theme.LocalDimensions
import com.kraker.pokemon.presentation.theme.dimensions
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun PokemonDetailTabs(pokemon: PokemonDetailDisplay) {
    var state by remember { mutableStateOf(PokemonDetailTab.ABOUT) }
    Column(modifier = Modifier.padding(
        horizontal = MaterialTheme.dimensions.spacingExtraLarge
    )) {
        TabRow(
            modifier = Modifier.padding(top = LocalDimensions.current.tabRowWhiteSpaceWidth),
            selectedTabIndex = state.ordinal,
            backgroundColor = Color.Transparent,
            indicator = {
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(it[state.ordinal])
                        .height(Dp(4.0F))
                        .background(color = colorResource(PrimaryColor), shape = RoundedCornerShape(8.dp)
                        ))
            }) {
            PokemonDetailTab.values().forEachIndexed { index, item ->
                Tab(
                    text = {
                        Text(item.text, style = MaterialTheme.typography.h3,
                            color = if (state.ordinal == index) colorResource(TextPrimaryColor) else colorResource(TextSecondaryColor)
                        )
                    },
                    selected = state.ordinal == index,
                    onClick = { state = PokemonDetailTab.values()[index] }
                )
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            when (state) {
                PokemonDetailTab.ABOUT -> {
                    PokemonDetailAbout(pokemon = pokemon)
                }
                PokemonDetailTab.STATS -> {
                    PokemonDetailStats(stats = pokemon.stats)
                }
                PokemonDetailTab.EVOLUTION -> {
                    PokemonDetailEvolutions(evolutions = pokemon.evolutions)
                }
            }
        }
    }
}
