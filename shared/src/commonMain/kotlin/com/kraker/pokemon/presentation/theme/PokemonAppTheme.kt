package com.kraker.pokemon.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kraker.pokemon.MR
import com.kraker.pokemon.MR.colors.PrimaryColor
import com.kraker.pokemon.MR.colors.SurfaceColor
import com.kraker.pokemon.MR.colors.SurfaceVariantColor
import com.kraker.pokemon.MR.colors.TextPrimaryColor
import dev.icerock.moko.resources.compose.asFont
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun PokemonAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val rubik = FontFamily(
        MR.fonts.rubik.light.asFont(weight = FontWeight.Light, style = FontStyle.Normal) as Font,
        MR.fonts.rubik.light_italic.asFont(weight = FontWeight.Light, style = FontStyle.Italic) as Font,
        MR.fonts.rubik.medium.asFont(weight = FontWeight.Medium, style = FontStyle.Normal) as Font,
        MR.fonts.rubik.medium_italic.asFont(weight = FontWeight.Medium, style = FontStyle.Italic) as Font,
        MR.fonts.rubik.bold.asFont(weight = FontWeight.Bold, style = FontStyle.Normal) as Font,
        MR.fonts.rubik.bold_italic.asFont(weight = FontWeight.Bold, style = FontStyle.Italic) as Font,
        MR.fonts.rubik.black.asFont(weight = FontWeight.Black, style = FontStyle.Normal) as Font,
        MR.fonts.rubik.black_italic.asFont(weight = FontWeight.Black, style = FontStyle.Italic) as Font,
    )

    val typography = Typography(
        h1 = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        ),
        h2 = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        h3 = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        ),
        subtitle1 = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        ),
        body1 = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        button = TextStyle(
            fontFamily = rubik,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )
    )

    MaterialTheme(
        colors = lightColors(
            primary = colorResource(PrimaryColor),
            surface = colorResource(SurfaceColor),
            background = colorResource(SurfaceVariantColor),
            onSurface = colorResource(TextPrimaryColor),
            onBackground = colorResource(TextPrimaryColor),
            onPrimary = Color.White
        ),
        typography = typography,
        shapes = Shapes,
        content = content
    )
}


