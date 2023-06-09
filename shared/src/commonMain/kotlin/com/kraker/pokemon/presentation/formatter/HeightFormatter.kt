package com.kraker.pokemon.presentation.formatter

class HeightFormatter {
    fun format(height: Int): String {
        return buildString { append(height / 10.0f).append(" ").append("m") }
    }
}
