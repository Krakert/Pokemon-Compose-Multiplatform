package com.kraker.pokemon.pokemon.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.kraker.pokemon.presentation.detail.model.PokemonDetailBadgeDisplay
import com.kraker.pokemon.presentation.theme.dimensions
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun TypeBadge(text: String, textColor: Color, backgroundColor: Color) {
    Box(modifier = Modifier.padding(
        horizontal = MaterialTheme.dimensions.spacingSmall,
        vertical = MaterialTheme.dimensions.spacingExtraSmall
    )) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(percent = 50))
                .background(color = backgroundColor)
                .padding(
                    horizontal = MaterialTheme.dimensions.spacingMedium,
                    vertical = MaterialTheme.dimensions.spacingExtraSmall
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.dimensions.spacingExtraSmall,
                    vertical = MaterialTheme.dimensions.spacingExtraSmall
                ),
                text = text,
                color = textColor,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun TypeBadgeRow(badges: List<PokemonDetailBadgeDisplay>) {
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = MaterialTheme.dimensions.spacingExtraLarge)) {
        badges.forEach {
            item {
                TypeBadge(text = it.name, textColor = colorResource(it.textColor), backgroundColor = colorResource(it.backGroundColor))
            }
        }
    }
}