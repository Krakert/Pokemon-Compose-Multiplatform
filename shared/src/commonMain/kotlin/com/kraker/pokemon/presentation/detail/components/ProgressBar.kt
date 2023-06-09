package com.kraker.pokemon.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.kraker.pokemon.MR.colors.TextPrimaryColor
import com.kraker.pokemon.MR.colors.TextSecondaryColor
import com.kraker.pokemon.presentation.detail.model.PokemonDetailStatsDisplay
import com.kraker.pokemon.presentation.theme.LocalDimensions
import com.kraker.pokemon.presentation.theme.dimensions
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun ProgressBarRow(stats: PokemonDetailStatsDisplay) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.dimensions.spacingLarge
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stats.name,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h3,
                color = colorResource(TextPrimaryColor),
                textAlign = TextAlign.Start
            )
            Text(
                text = stats.baseNumber.toString(),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.body2,
                color = Color.Gray,
                textAlign = TextAlign.End
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProgressBar(stats.progress)
            LinearProgressIndicator()
        }
    }
}

@Composable
fun ProgressBar(progress: Float) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .height(LocalDimensions.current.linearIndicatorHeight)
            .background(colorResource(TextSecondaryColor))
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .fillMaxHeight()
                .background(MaterialTheme.colors.primary)
                .fillMaxWidth(1f * progress)
        )
    }
}