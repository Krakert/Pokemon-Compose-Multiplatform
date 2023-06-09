package com.kraker.pokemon.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kraker.pokemon.MR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource

@Composable
fun pokemonBadgeId(): TextStyle {
    return TextStyle(
        fontFamily = fontFamilyResource(MR.fonts.rubik.medium),
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp
    )
}

@Composable
fun pokemonDetailsId(): TextStyle {
    return TextStyle(
        fontFamily = fontFamilyResource(MR.fonts.rubik.light),
        fontWeight = FontWeight.Light,
        fontSize = 24.sp,
        color = colorResource(MR.colors.TextSecondaryColor)
    )
}

@Composable
fun searchQuery(): TextStyle {
    return TextStyle(
        fontFamily = fontFamilyResource(MR.fonts.rubik.light),
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        color = colorResource(MR.colors.TextPrimaryColor)
    )
}

@Composable
fun searchHint(): TextStyle {
    return TextStyle(
        fontFamily = fontFamilyResource(MR.fonts.rubik.light),
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        color = colorResource(MR.colors.TextSecondaryColor)
    )
}