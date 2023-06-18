package com.kraker.pokemon.presentation.overview.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.kraker.pokemon.MR
import com.kraker.pokemon.MR.colors.TextPrimaryColor
import com.kraker.pokemon.MR.colors.TextSecondaryColor
import com.kraker.pokemon.presentation.theme.LocalDimensions
import com.kraker.pokemon.presentation.theme.searchQuery
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun OverviewSearchBar(onQueryChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    val query = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    onQueryChanged(query.value)

    TextField(
        modifier = modifier
            .height(90.dp)
            .shadow(elevation = LocalDimensions.current.searchBarElevation, shape = MaterialTheme.shapes.large),
        value = query.value,
        onValueChange = {
            query.value = it
        },
        leadingIcon = {
            if (query.value.isNotEmpty()) {
                IconButton(onClick = {
                    focusManager.clearFocus()
                    query.value = ""
                }) {
                    Icon(
                        painter = painterResource(MR.images.ic_back),
                        contentDescription = null,
                        tint = colorResource(TextPrimaryColor)
                    )
                }
            } else {
                Icon(
                    painter = painterResource(MR.images.ic_search),
                    contentDescription = null,
                    tint = colorResource(TextPrimaryColor)
                )
                focusManager.clearFocus()
            }
        },
        placeholder = {
            Text(text = stringResource(MR.strings.overview_search_hint))
        },
        shape = MaterialTheme.shapes.large,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            textColor = MaterialTheme.colors.onSurface,
            placeholderColor = colorResource(TextSecondaryColor),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.onSurface
        ),
        textStyle = searchQuery(),
    )
}
