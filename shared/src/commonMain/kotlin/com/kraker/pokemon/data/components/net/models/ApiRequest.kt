package com.kraker.pokemon.data.components.net.models

import com.kraker.pokemon.data.DataConfig.POKEMON_API_URL

data class ApiRequest<T>(
    val method: ApiMethod,
    val url: String = POKEMON_API_URL,
    val path: String,
    val requestBody: Any? = null,
    val parameters: List<Query>? = null
)
