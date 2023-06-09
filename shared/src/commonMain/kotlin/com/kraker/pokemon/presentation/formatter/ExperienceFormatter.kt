package com.kraker.pokemon.presentation.formatter

class ExperienceFormatter {
    fun format(baseExperience: Int): String {
        return buildString { append(baseExperience).append(" ").append("XP") }
    }
}
