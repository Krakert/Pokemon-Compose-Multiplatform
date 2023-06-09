package com.kraker.pokemon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.moriatsushi.insetsx.rememberWindowInsetsController
import com.kraker.pokemon.presentation.components.Screen
import com.kraker.pokemon.presentation.detail.PokemonDetailScreen
import com.kraker.pokemon.presentation.overview.PokemonOverviewScreen
import com.kraker.pokemon.presentation.theme.PokemonAppTheme
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
internal fun App() {
    val navigator = rememberNavigator()

    val windowInsetsController = rememberWindowInsetsController()

    LaunchedEffect(Unit) {
        windowInsetsController?.apply {
            setStatusBarContentColor(dark = false)
            setNavigationBarsContentColor(dark = false)
        }
    }

    PokemonAppTheme {
        NavHost(navigator = navigator, initialRoute = Screen.Overview.route) {
            scene(Screen.Overview.route) {
                PokemonOverviewScreen(navigator = navigator)
            }
            scene(Screen.Detail.route + "/{id}") {
                val id = it.path<Int>("id")
                if (id != null) {
                    PokemonDetailScreen(
                        id = id.toInt(),
                        navigator = navigator
                    )
                }
            }
        }
    }
}