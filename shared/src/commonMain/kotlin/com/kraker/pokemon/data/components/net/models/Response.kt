package com.kraker.pokemon.data.components.net.models

import io.ktor.client.statement.HttpResponse

data class Response<T>(
    val response: HttpResponse
)
