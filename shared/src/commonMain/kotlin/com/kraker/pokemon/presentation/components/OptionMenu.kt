package com.kraker.pokemon.presentation.components

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
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

@Composable
fun OptionMenu() {
    val surroundingBackground = remember { Animatable(Color.Transparent) }
    val targetSurroundingBackground = Color.Gray

    LaunchedEffect(Unit) {
        surroundingBackground.animateTo(targetSurroundingBackground)
    }
    Box(modifier = Modifier.fillMaxSize().background(surroundingBackground.value)) {

        Column(modifier = Modifier.fillMaxHeight()) {
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text("TESt")
            }
        }
    }

    LaunchedEffect(Unit){
        surroundingBackground.animateTo(Color.Transparent)
    }
}