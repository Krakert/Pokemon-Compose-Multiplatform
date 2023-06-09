package com.kraker.pokemon.presentation.extension

fun String.kebabCaseTo(replacement: String = " ", capitalize: Boolean = true): String {
    return if (capitalize) {
        replace("-", replacement).replaceFirstChar {
            if (it.isLowerCase()) it.uppercase() else it.toString()
        }
    } else {
        replace("-", replacement)
    }
}

fun String.capitalize() = replaceFirstChar { if (it.isLowerCase()) it.uppercase() else it.toString() }

fun List<String>.delimiterStringFormatter(delimiter: String, capitalize: Boolean): String {
    return joinToString(delimiter) { word ->
        if (capitalize) {
            word.replaceFirstChar { if (it.isLowerCase()) it.uppercase() else it.toString() }
        } else {
            word
        }
    }
}