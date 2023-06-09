package com.kraker.pokemon.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.seiko.imageloader.rememberAsyncImagePainter
import com.kraker.pokemon.MR.colors.SurfaceColor
import com.kraker.pokemon.presentation.detail.components.DetailsTopBar
import com.kraker.pokemon.presentation.detail.components.Header
import com.kraker.pokemon.pokemon.detail.components.TypeBadgeRow
import com.kraker.pokemon.presentation.OnDisplay
import com.kraker.pokemon.presentation.OnError
import com.kraker.pokemon.presentation.OnLoading
import com.kraker.pokemon.presentation.components.Screen
import com.kraker.pokemon.presentation.detail.model.PokemonDetailDisplay
import dev.icerock.moko.resources.compose.colorResource
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun PokemonDetailScreen(
    id: Int,
    navigator: Navigator,
) {
    val viewModel = viewModel(PokemonDetailViewModel::class) {
        PokemonDetailViewModel()
    }

    LaunchedEffect(Unit) {
        viewModel.fetchDetails(id)
    }

    val content by viewModel.pokemonDetailDisplayState.collectAsState()

    when (val state = content) {
        is OnDisplay -> {
            PokemonDetailDisplayScreen(
                pokemon = state.display,
                navigator = navigator,
                viewModel = viewModel,
            )
        }

        is OnError -> {
            Text(text = state.errorDisplay.toString())
        }

        OnLoading -> Box(
            Modifier
                .fillMaxSize()
                .background(colorResource(SurfaceColor))
        ) {

        }
    }

}

@Composable
fun PokemonDetailDisplayScreen(
    pokemon: PokemonDetailDisplay,
    navigator: Navigator,
    viewModel: PokemonDetailViewModel,
) {
    var favorite by remember { mutableStateOf(pokemon.isFavourite) }


    Scaffold(topBar = {
        DetailsTopBar(
            isFavorite = favorite,
            onNavigateBack = {
                navigator.navigate(route = Screen.Overview.route, NavOptions(launchSingleTop = true))
            },
            onAddToFavorite = {
                viewModel.addPokemonToFavourites(pokemon.id)
                favorite = true
            },
            onRemoveFromFavorite = {
                viewModel.removePokemonFromFavourites(pokemon.id)
                favorite = false
            },
        )
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colors.surface
        ) {

            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .weight(0.55f)
                ) {
                    Column {
                        Header(name = pokemon.name.text, id = pokemon.idWithLeadingZeros.text)
                        TypeBadgeRow(badges = pokemon.badges)
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        PokemonDetailTabs(pokemon = pokemon)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f), horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier.weight(0.4f), contentAlignment = Alignment.BottomCenter) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            pokemon.imageUrl
                        ),
                        contentDescription = pokemon.name.text,
                        modifier = Modifier.size(225.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
                Spacer(modifier = Modifier.weight(0.5f))
            }
        }
    }
}