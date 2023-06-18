package com.kraker.pokemon.presentation.components

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex

@Composable
fun OptionMenu(value: Int, onBackPress: () -> Unit) {
    val surroundingBackground = remember { Animatable(Color.Transparent) }
    val interactionSource = remember { MutableInteractionSource() }

    var targetSurroundingBackground = Color.Gray.copy(alpha = 0.5F)

    LaunchedEffect(Unit) {
        surroundingBackground.animateTo(targetSurroundingBackground)
        targetSurroundingBackground = Color.Transparent
    }
    Box(modifier = Modifier.fillMaxSize().background(surroundingBackground.value).zIndex(1F).clickable(
        interactionSource = interactionSource,
        indication = null
    ) {
        onBackPress()
    }) {

        Column(modifier = Modifier.fillMaxHeight()) {
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text("Test $value")
            }
        }
    }
}