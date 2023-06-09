package com.kraker.pokemon.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moriatsushi.insetsx.ExperimentalSoftwareKeyboardApi
import com.moriatsushi.insetsx.safeDrawing
import com.kraker.pokemon.MR
import com.kraker.pokemon.MR.colors.TextPrimaryColor
import com.kraker.pokemon.presentation.theme.dimensions
import com.kraker.pokemon.presentation.theme.pokemonDetailsId
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@OptIn(ExperimentalSoftwareKeyboardApi::class)
@Composable
fun DetailsTopBar(
    isFavorite: Boolean,
    onNavigateBack: () -> Unit,
    onAddToFavorite: () -> Unit,
    onRemoveFromFavorite: () -> Unit,
) {
    val favorite = remember { mutableStateOf(isFavorite) }
    TopAppBar(
        modifier = Modifier
            .windowInsetsPadding(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal)
        ),
        backgroundColor = MaterialTheme.colors.surface,
        contentPadding = PaddingValues(
            horizontal = MaterialTheme.dimensions.spacingLarge,
            vertical = MaterialTheme.dimensions.spacingMedium
        ),
        elevation = 0.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { onNavigateBack() }) {
                    Icon(
                        painter = painterResource(MR.images.ic_back),
                        tint = colorResource(TextPrimaryColor),
                        contentDescription = null
                    )
                }
                IconButton(onClick = {
                    favorite.value = !favorite.value
                    if (favorite.value) {
                        onAddToFavorite()
                    } else {
                        onRemoveFromFavorite()
                    }
                }) {
                    if (isFavorite) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            tint = colorResource(TextPrimaryColor),
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            tint = colorResource(TextPrimaryColor),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Header(name: String, id: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.dimensions.spacingExtraLarge),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, style = MaterialTheme.typography.h1, color = colorResource(TextPrimaryColor))
        if (id != null) {
            Text(text = id, style = pokemonDetailsId(), color = Color.Gray)
        }
    }
}