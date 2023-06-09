package com.kraker.pokemon.presentation.detail.mapper

import com.kraker.pokemon.MR.colors.ElectricYellow
import com.kraker.pokemon.MR.colors.FireRed
import com.kraker.pokemon.MR.colors.GrassGreen
import com.kraker.pokemon.MR.colors.PrimaryColor
import com.kraker.pokemon.MR.colors.SurfaceColor
import com.kraker.pokemon.MR.colors.TextPrimaryColor
import com.kraker.pokemon.presentation.detail.model.PokemonDetailBadgeDisplay

class PokemonDetailBadgeDisplayMapper {
    fun map(type: String):
        PokemonDetailBadgeDisplay {
        with(type) {
            var textColor = SurfaceColor
            var backgroundColor = PrimaryColor
            when (this) {
                "grass" -> {
                    textColor = TextPrimaryColor
                    backgroundColor = GrassGreen
                }
                "fire" -> {
                    textColor = SurfaceColor
                    backgroundColor = FireRed
                }
                "electric" -> {
                    textColor = TextPrimaryColor
                    backgroundColor = ElectricYellow
                }
            }
            return PokemonDetailBadgeDisplay(
                name = this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                textColor = textColor,
                backGroundColor = backgroundColor
            )
        }
    }
}
