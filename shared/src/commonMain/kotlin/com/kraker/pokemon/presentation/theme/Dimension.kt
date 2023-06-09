package com.kraker.pokemon.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions {
    val radiusSmall: Dp = 4.dp
    val radiusMedium: Dp = 8.dp
    val radiusLarge: Dp = 12.dp
    val spacingExtraSmall: Dp = 2.dp
    val spacingSmall: Dp = 4.dp
    val spacingMedium: Dp = 8.dp
    val spacingLarge: Dp = 12.dp
    val spacingExtraLarge: Dp = 24.dp
}

class CustomDimensions {
    val bottomBarButtonWidth: Dp = 64.dp
    val tabRowWhiteSpaceWidth: Dp = 56.dp
    val linearIndicatorHeight: Dp = 4.dp
    val boxEvolutionSize: Dp = 72.dp
    val overviewImageSize: Dp = 250.dp
    val evolutionImageSize: Dp = 64.dp
    val bottomBarHeight: Dp = 100.dp
    val bottomBarElevation: Dp = 10.dp
    val topBarElevation: Dp = 10.dp
    val searchBarElevation: Dp = 8.dp
    val searchBarHeight: Dp = 48.dp
    val pokemonLoaderSize: Dp = 75.dp
    val pokemonLoaderSpacing: Dp = 75.dp
    val pokemonListSpacing: Dp = 16.dp
}

private val LocalMaterialDimensions = staticCompositionLocalOf { Dimensions() }

val LocalDimensions = staticCompositionLocalOf { CustomDimensions() }

val MaterialTheme.dimensions: Dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalMaterialDimensions.current
