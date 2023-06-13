package com.kraker.pokemon.presentation.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.kraker.pokemon.MR
import com.kraker.pokemon.presentation.detail.components.CenterElement
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewContent
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewItemDisplay
import com.kraker.pokemon.presentation.theme.LocalDimensions
import com.kraker.pokemon.presentation.theme.dimensions
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun PokemonOverviewList(
    pokemonList: List<PokemonOverviewItemDisplay>,
    onBottomReached: () -> Unit,
    liftOnScrollChanged: (Boolean) -> Unit,
    onNavigateToDetails: (Int) -> Unit,
    onShowOptions: (Int) -> Unit,
    state: PokemonOverviewContent
) {
    val listState = rememberLazyGridState().apply {
        OnBottomReached(buffer = 4) {
            onBottomReached()
        }
        liftOnScrollChanged(firstVisibleItemScrollOffset > 0)
    }

    if (state == PokemonOverviewContent.FAVORITES && pokemonList.isEmpty()) {
        CenterElement {
            Text(stringResource(MR.strings.empty_favourite))
        }
    } else {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            state = listState,
            contentPadding = PaddingValues(MaterialTheme.dimensions.spacingExtraLarge),
            horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.pokemonListSpacing),
            verticalArrangement = Arrangement.spacedBy(LocalDimensions.current.pokemonListSpacing)
        ) {
            items(pokemonList) { item ->
                PokemonOverviewItemCard(
                    item = item,
                    onClick = { onNavigateToDetails(item.id) },
                    onShowOptions = { onShowOptions(item.id) }
                )
            }
        }
    }
}

@Composable
private fun LazyGridState.OnBottomReached(
    buffer: Int = 0,
    onLoadMore: () -> Unit
) {
    require(buffer >= 0) { "Buffer cannot be negative, but was $buffer" }
    val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
    val shouldLoadMore = lastVisibleItem?.let {
        lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
    } ?: true

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) onLoadMore()
    }
}

