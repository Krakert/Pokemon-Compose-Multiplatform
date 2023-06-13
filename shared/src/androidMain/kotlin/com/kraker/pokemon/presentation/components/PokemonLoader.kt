package com.kraker.pokemon.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kraker.pokemon.MR
import com.kraker.pokemon.presentation.theme.LocalDimensions


@Composable
actual fun PokemonLoader(modifier: Modifier) {
    val loadingAnim by rememberLottieComposition(LottieCompositionSpec.RawRes(MR.files.anim_loading.rawResId))
    val loadingProgress by animateLottieCompositionAsState(
        loadingAnim,
        iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        loadingAnim,
        { loadingProgress },
        modifier
            .padding(LocalDimensions.current.pokemonLoaderSpacing)
            .size(LocalDimensions.current.pokemonLoaderSize)
    )
}