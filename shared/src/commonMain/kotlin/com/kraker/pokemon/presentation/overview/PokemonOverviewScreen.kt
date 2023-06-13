package com.kraker.pokemon.presentation.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kraker.pokemon.MR
import com.kraker.pokemon.presentation.OnDisplay
import com.kraker.pokemon.presentation.OnError
import com.kraker.pokemon.presentation.OnLoading
import com.kraker.pokemon.presentation.components.PokemonLoader
import com.kraker.pokemon.presentation.components.Screen
import com.kraker.pokemon.presentation.detail.components.CenterElement
import com.kraker.pokemon.presentation.overview.component.OverviewBottomBar
import com.kraker.pokemon.presentation.overview.component.OverviewTopBar
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewContent
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewItemDisplay
import com.moriatsushi.insetsx.ExperimentalSoftwareKeyboardApi
import com.moriatsushi.insetsx.safeDrawing
import dev.icerock.moko.resources.compose.stringResource
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.viewmodel.viewModel

@OptIn(ExperimentalSoftwareKeyboardApi::class)
@Composable
fun PokemonOverviewScreen(
    navigator: Navigator,
) {

    val viewModel = viewModel(PokemonOverviewViewModel::class) {
        PokemonOverviewViewModel()
    }

    val pageState by viewModel.pokemonOverviewPageState.collectAsState()
    val contentState by viewModel.pokemonOverviewContentState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val title = remember { mutableStateOf("") }
    val showElevation = remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(false) }


    if (pageState == PokemonOverviewContent.ALL_POKEMON) {
        title.value = stringResource(MR.strings.overview_pokemon_title)
        LaunchedEffect(Unit) { viewModel.fetchPokemonOverview() }
    } else if (pageState == PokemonOverviewContent.FAVORITES) {
        title.value = stringResource(MR.strings.overview_favorites_title)
        LaunchedEffect(Unit) { viewModel.fetchFavourite() }
    }

    Scaffold(topBar = {
        OverviewTopBar(title = title.value,
            showSearch = pageState == PokemonOverviewContent.ALL_POKEMON,
            onSearch = {})
    }, bottomBar = {
        OverviewBottomBar(
            selectedContent = pageState,
            onContentChanged = { viewModel.changeContent(it) })
    }) { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(paddingValues).windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
            ),
            color = MaterialTheme.colors.surface
        ) {
            when (val state = contentState) {
                is OnDisplay -> {
                    PokemonOverviewPokemonList(
                        state = pageState,
                        content = state.display.pokemons,
                        onLoadMore = {
                            if (pageState == PokemonOverviewContent.ALL_POKEMON) {
                                viewModel.fetchNextBatch()
                            }
                        },
                        showLoading = isLoading,
                        liftOnScrollChanged = { showElevation.value = it },
                        onShowOptions = { visible = true },
                        onNavigateToDetails = {
                            navigator.navigate(route = Screen.Detail.route + "/$it")
                        })
                }
                is OnError -> {

                }
                OnLoading -> {
                    CenterElement {
                        Box(Modifier.fillMaxSize()) {
                            PokemonLoader(Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PokemonOverviewPokemonList(
    content: List<PokemonOverviewItemDisplay>,
    onLoadMore: () -> Unit,
    showLoading: Boolean,
    state: PokemonOverviewContent,
    liftOnScrollChanged: (Boolean) -> Unit,
    onNavigateToDetails: (Int) -> Unit,
    onShowOptions: (Int) -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        PokemonOverviewList(
            state = state,
            pokemonList = content,
            onBottomReached = { onLoadMore() },
            liftOnScrollChanged = liftOnScrollChanged,
            onNavigateToDetails = onNavigateToDetails,
            onShowOptions = onShowOptions,
        )
        if (showLoading) PokemonLoader(Modifier.align(Alignment.Center))
    }
}
