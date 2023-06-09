package com.kraker.pokemon.presentation.formatter

class IdFormatter {

    companion object {
        private const val MIN_DIGITS = 3
    }

    fun intToStringWithLeadingZeros(digit: Int): String {
        return digit.toString().padStart(MIN_DIGITS, '0')
    }

    fun intToStringWithLeadingZeros(minimalDigits: Int, digit: Int): String {
        return digit.toString().padStart(minimalDigits, '0')
    }
}
