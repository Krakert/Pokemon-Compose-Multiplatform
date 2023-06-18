package com.kraker.pokemon.presentation.overview.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moriatsushi.insetsx.ExperimentalSoftwareKeyboardApi
import com.moriatsushi.insetsx.safeDrawing
import com.kraker.pokemon.presentation.theme.LocalDimensions
import com.kraker.pokemon.presentation.theme.dimensions

@OptIn(ExperimentalSoftwareKeyboardApi::class)
@Composable
fun OverviewTopBar(title: String, showSearch: Boolean, onSearch: (String) -> Unit) {
    val showTitle = remember { mutableStateOf(true) }
    val height by animateDpAsState(targetValue = if (showTitle.value) 175.dp else 140.dp)

    TopAppBar(
        modifier = Modifier.height(height)
            .background(MaterialTheme.colors.surface)
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal)
            ),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        contentPadding = PaddingValues(
            horizontal = MaterialTheme.dimensions.spacingExtraLarge,
            vertical = MaterialTheme.dimensions.spacingMedium
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            if (showSearch) {
                OverviewSearchBar(
                    onQueryChanged = { query ->
                        onSearch(query)
                        showTitle.value = query.isEmpty()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LocalDimensions.current.searchBarHeight)
                )
            }
            if (showTitle.value) {
                if (showSearch) {
                    Spacer(modifier = Modifier.height(MaterialTheme.dimensions.spacingExtraLarge))
                }
                Text(text = title, style = MaterialTheme.typography.h2, color = MaterialTheme.colors.onSurface)
            }
        }
    }
}
