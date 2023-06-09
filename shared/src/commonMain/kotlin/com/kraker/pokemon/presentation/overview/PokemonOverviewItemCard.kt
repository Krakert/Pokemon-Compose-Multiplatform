package com.kraker.pokemon.presentation.overview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberAsyncImagePainter
import com.kraker.pokemon.MR
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewItemDisplay
import com.kraker.pokemon.presentation.theme.LocalDimensions
import com.kraker.pokemon.presentation.theme.dimensions
import com.kraker.pokemon.presentation.theme.pokemonBadgeId
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun PokemonOverviewItemCard(
    modifier: Modifier = Modifier,
    item: PokemonOverviewItemDisplay,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.clickable { onClick(item.id) }) {
            Box(
                Modifier
                    .background(MaterialTheme.colors.background)
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        item.imageUrl
                    ),
                    contentDescription = item.name,
                    modifier = Modifier.size(LocalDimensions.current.overviewImageSize),
                    contentScale = ContentScale.Crop,
                )
                PokemonIdBadge(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(MaterialTheme.dimensions.spacingMedium),
                    id = item.idWithLeadingZeros
                )
            }
            PokemonDetails(id = item.id, name = item.name)
        }
    }
}

@Composable
private fun PokemonIdBadge(modifier: Modifier = Modifier, id: String) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colors.primary, shape = MaterialTheme.shapes.medium)
            .padding(
                horizontal = MaterialTheme.dimensions.spacingSmall,
                vertical = MaterialTheme.dimensions.spacingExtraSmall
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = id,
            color = MaterialTheme.colors.onPrimary,
            style = pokemonBadgeId()
        )
    }
}

@Composable
private fun PokemonDetails(id: Int, name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                horizontal = MaterialTheme.dimensions.spacingLarge,
                vertical = MaterialTheme.dimensions.spacingLarge
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.subtitle1,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.weight(1f, true)
        )
        IconButton(
            onClick = { /*TODO*/ },
            Modifier.size(24.dp)
        ) {
            Icon(painter = painterResource(MR.images.ic_menu), contentDescription = null)
        }
    }
}
