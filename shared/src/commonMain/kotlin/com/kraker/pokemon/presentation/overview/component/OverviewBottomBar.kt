package com.kraker.pokemon.presentation.overview.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.moriatsushi.insetsx.ExperimentalSoftwareKeyboardApi
import com.moriatsushi.insetsx.safeDrawing
import com.kraker.pokemon.MR
import com.kraker.pokemon.MR.colors.IconTint
import com.kraker.pokemon.MR.colors.IconTintSelected
import com.kraker.pokemon.MR.colors.PrimaryColor
import com.kraker.pokemon.MR.colors.SurfaceColor
import com.kraker.pokemon.MR.colors.TextPrimaryColor
import com.kraker.pokemon.MR.colors.TextSecondaryColor
import com.kraker.pokemon.presentation.overview.model.PokemonOverviewContent
import com.kraker.pokemon.presentation.theme.LocalDimensions
import com.kraker.pokemon.presentation.theme.LocalShapes
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalSoftwareKeyboardApi::class)
@Composable
fun OverviewBottomBar(
    modifier: Modifier = Modifier,
    selectedContent: PokemonOverviewContent,
    onContentChanged: (PokemonOverviewContent) -> Unit
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .height(LocalDimensions.current.bottomBarHeight)
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal
                )
            ),
        elevation = 0.dp
    ) {
        BottomBarItem(
            modifier = Modifier
                .align(Alignment.Top)
                .weight(0.5F),
            iconRes = painterResource(MR.images.ic_pokeball) ,
            text = stringResource(MR.strings.overview_item_pokemons),
            isSelected = selectedContent == PokemonOverviewContent.ALL_POKEMON,
            onClick = { onContentChanged(PokemonOverviewContent.ALL_POKEMON) }
        )
        BottomBarItem(
            modifier = Modifier
                .align(Alignment.Top)
                .weight(0.5F),
            iconRes = painterResource(MR.images.ic_favorite),
            text = stringResource(MR.strings.overview_item_favorites),
            isSelected = selectedContent == PokemonOverviewContent.FAVORITES,
            onClick = { onContentChanged(PokemonOverviewContent.FAVORITES) }
        )
    }
}

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    iconRes: Painter
) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = onClick,
            modifier = Modifier.width(LocalDimensions.current.bottomBarButtonWidth),
            shape = LocalShapes.current.bottomBarButton,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (isSelected) colorResource(PrimaryColor) else colorResource(SurfaceColor),
                contentColor = if (isSelected) colorResource(IconTintSelected) else colorResource(IconTint)
            ),
            elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Icon(painter = iconRes, contentDescription = null)
        }
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = if (isSelected) colorResource(TextPrimaryColor) else colorResource(TextSecondaryColor)
        )
    }
}
