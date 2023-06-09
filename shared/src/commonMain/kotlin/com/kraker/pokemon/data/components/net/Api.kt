package com.kraker.pokemon.data.components.net

import com.kraker.pokemon.data.components.net.models.ApiRequest
import com.kraker.pokemon.data.components.net.models.Response

interface Api {
    suspend fun <T>request(request: ApiRequest<T>): Response<T>

    suspend fun <T>requestNoBaseURL(request: ApiRequest<T>): Response<T>
}
