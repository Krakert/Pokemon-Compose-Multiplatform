package com.kraker.pokemon.data.components.net.mapper

import com.kraker.pokemon.data.components.net.models.Response
import com.kraker.pokemon.domain.response.AuthException
import com.kraker.pokemon.domain.response.BackendException
import io.ktor.client.call.body
import io.ktor.http.isSuccess

class ResponseMapper {
    suspend inline fun <reified T> map(httpResponse: Response<T>): Result<T> {
        return if (httpResponse.response.status.isSuccess()) {
            Result.success(httpResponse.response.body())
        } else if (httpResponse.response.status.value == 401) {
            Result.failure(AuthException())
        } else {
            Result.failure(BackendException(errorCode = httpResponse.response.status.value))
        }
    }
}
