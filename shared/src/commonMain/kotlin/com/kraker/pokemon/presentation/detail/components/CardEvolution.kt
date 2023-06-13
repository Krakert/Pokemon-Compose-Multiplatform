package com.kraker.pokemon.presentation.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberAsyncImagePainter
import com.kraker.pokemon.MR.colors.IconTintSelected
import com.kraker.pokemon.MR.colors.PrimaryColor
import com.kraker.pokemon.MR.colors.TextPrimaryColor
import com.kraker.pokemon.presentation.detail.model.PokemonDetailEvolutionDisplay
import com.kraker.pokemon.presentation.theme.LocalDimensions
import com.kraker.pokemon.presentation.theme.dimensions
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun CardEvolution(evolution: PokemonDetailEvolutionDisplay) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.dimensions.spacingMedium
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(LocalDimensions.current.boxEvolutionSize)
                    .background(MaterialTheme.colors.surface)
            ) {
                CenterElement {
                    Image(
                        painter = rememberAsyncImagePainter(
                            evolution.imageUrl
                        ),
                        contentDescription = evolution.name,
                        modifier = Modifier.size(LocalDimensions.current.evolutionImageSize),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.dimensions.spacingMedium,
                    vertical = MaterialTheme.dimensions.spacingMedium
                )
            ) {
                Box(
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.dimensions.spacingMedium,
                            vertical = MaterialTheme.dimensions.spacingSmall
                        ),
                ) {
                    Box(
                        modifier = Modifier
                            .background(color = colorResource(PrimaryColor), shape = RoundedCornerShape(25))
                            .padding(
                                horizontal = MaterialTheme.dimensions.spacingMedium,
                                vertical = MaterialTheme.dimensions.spacingExtraSmall
                            ),
                    ) {
                        Text(
                            text = evolution.id,
                            color = colorResource(IconTintSelected),
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(
                        horizontal = MaterialTheme.dimensions.spacingMedium,
                        vertical = MaterialTheme.dimensions.spacingSmall
                    ),
                    text = evolution.name,
                    style = MaterialTheme.typography.h3,
                    color = colorResource(TextPrimaryColor)
                )
            }
        }
    }
}

@Composable
fun DashedVerticalLine(
    dots: Int = 4,
    width: Dp = 4.dp,
    smallDotHeight: Dp = 4.dp,
    bigDotHeight: Dp = 6.dp,
    spacing: Dp = 4.dp,
    radiusPercentage: Int = 25,
    color: Color = Color.Gray.copy(alpha = 0.6f),
    offsetPercentage: Float = 0.25f,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(Modifier.weight(offsetPercentage))
        Row(Modifier.weight(1 - offsetPercentage)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing),
            ) {
                for (i in 1..dots) {
                    if (i in 2 until dots) {
                        Box(
                            modifier = Modifier
                                .size(width, bigDotHeight)
                                .clip(RoundedCornerShape(radiusPercentage))
                                .background(color)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(width, smallDotHeight)
                                .clip(RoundedCornerShape(radiusPercentage))
                                .background(color)
                        )
                    }
                }
            }
        }
    }
}