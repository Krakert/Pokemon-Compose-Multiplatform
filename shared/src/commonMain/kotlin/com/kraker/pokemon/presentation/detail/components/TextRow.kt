package com.kraker.pokemon.presentation.detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.kraker.pokemon.MR.colors.TextPrimaryColor
import com.kraker.pokemon.presentation.theme.dimensions
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun TextRow(header: String, text: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(
            vertical = MaterialTheme.dimensions.spacingMedium),
        verticalAlignment = Alignment.CenterVertically) {

        Text(text = header,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h3,
            color = colorResource(TextPrimaryColor),
            textAlign = TextAlign.Start)

        Text(text = text,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.body2,
            color = Color.Gray,
            textAlign = TextAlign.Start)
    }
}