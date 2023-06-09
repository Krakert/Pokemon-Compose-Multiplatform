package com.kraker.pokemon.domain.response

data class BackendException(
    val errorCode: Int
) : Exception()
