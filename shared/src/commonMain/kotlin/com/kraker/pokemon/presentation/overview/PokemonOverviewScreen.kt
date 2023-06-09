package com.kraker.pokemon.presentation.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.moriatsushi.insetsx.ExperimentalSoftwareKeyboardApi
import com.moriatsushi.insetsx.safeDrawing
import com.kraker.pokemon.MR
import com.kraker.pokemon.MR.colors.SurfaceColor
import com.kraker.pokemon.pokemon.presentation.components.CenterElement
import com.kraker.pokemon.presentation.OnDisplay
import com.kraker.pokemon.presentation.OnError
import com.kraker.pokemon.presentation.OnLoading
import com.kraker.pokemon.presentation.components.Screen
import com.kraker.pokemon.presentation.overview.component.OverviewBottomBar
import com.kraker.pokemon.presentation.overview.component.OverviewTopBar
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewContent
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewDisplay
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewItemDisplay
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun PokemonOverviewScreen(
    navigator: Navigator,
) {

    val viewModel = viewModel(PokemonOverviewViewModel::class) {
        PokemonOverviewViewModel()
    }

    val content by viewModel.pokemonOverviewDisplayState.collectAsState()

    when (val state = content) {
        is OnDisplay -> {
            PokemonOverviewDisplay(navigator = navigator, display = state.display, viewModel = viewModel)
        }

        is OnError -> {
            Box(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.surface)
            ) {
                CenterElement {
                    Text(text = stringResource(MR.strings.error_generic))
                    Button(onClick = { viewModel.fetchNextBatch() }) {
                        Text(text = stringResource(MR.strings.text_button_retry))
                    }
                }
            }
        }

        OnLoading -> {
            Box(
                modifier = Modifier.fillMaxSize().background(colorResource(SurfaceColor))
            ) {
                //                PokemonLoader(Modifier.align(Alignment.Center))
            }
        }
    }
}

@OptIn(ExperimentalSoftwareKeyboardApi::class)
@Composable
fun PokemonOverviewDisplay(
    viewModel: PokemonOverviewViewModel,
    display: PokemonOverviewDisplay,
    navigator: Navigator,
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val showElevation = remember { mutableStateOf(false) }
    val title = remember { mutableStateOf("") }

    Scaffold(topBar = {
        OverviewTopBar(title = title.value,
            showSearch = display.selectedContent == PokemonOverviewContent.ALL_POKEMON,
            onSearch = {})
    }, bottomBar = {
        OverviewBottomBar(
            selectedContent = display.selectedContent,
            onContentChanged = { viewModel.changeContent(it) })
    }) { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(paddingValues).windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
            ),
            color = MaterialTheme.colors.surface
        ) {
            when (display.selectedContent) {
                PokemonOverviewContent.ALL_POKEMON -> {
                    title.value = stringResource(MR.strings.overview_pokemon_title)
                    PokemonOverviewPokemonList(
                        content = display.pokemons,
                        onLoadMore = viewModel::fetchNextBatch,
                        showLoading = isLoading,
                        liftOnScrollChanged = { showElevation.value = it }) {
                        navigator.navigate(route = Screen.Detail.route + "/$it")
                    }
                }
                PokemonOverviewContent.FAVORITES -> {
                    title.value = stringResource(MR.strings.overview_favorites_title)
                    PokemonOverviewPokemonList(
                        content = display.pokemons,
                        onLoadMore = {  },
                        showLoading = isLoading,
                        liftOnScrollChanged = { showElevation.value = it }) {
                        navigator.navigate(route = Screen.Detail.route + "/$it")
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
    liftOnScrollChanged: (Boolean) -> Unit,
    onNavigateToDetails: (Int) -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        PokemonOverviewList(
            pokemonList = content,
            onBottomReached = { onLoadMore() },
            liftOnScrollChanged = liftOnScrollChanged,
            onNavigateToDetails = onNavigateToDetails
        )
    }
}
