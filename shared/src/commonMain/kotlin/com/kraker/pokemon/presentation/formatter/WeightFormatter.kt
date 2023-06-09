package com.kraker.pokemon.presentation.formatter

class WeightFormatter {
    fun format(weight: Int): String {
        return buildString { append(weight / 10.0f).append(" ").append("Kg") }
    }
}
