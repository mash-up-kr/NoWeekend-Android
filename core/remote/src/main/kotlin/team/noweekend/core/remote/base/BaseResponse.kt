package team.noweekend.core.remote.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NWKResponse<T>(
    @SerialName("result")
    val result: String,
    @SerialName("data")
    val data: T,
    @SerialName("error")
    val error: NWKErrorResponse?,
)

@Serializable
data class NWKErrorResponse(
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
)

data class NWKApiException(
    val errorResponse: NWKErrorResponse?,
) : RuntimeException() {
    override val message: String
        get() = errorResponse?.message.orEmpty()
}

suspend fun <T> callApi(
    execute: suspend () -> NWKResponse<T>,
): T {
    return runCatching {
        execute().data ?: throw NoSuchElementException()
    }.getOrElse { e ->
        throw NWKApiErrorParser.parse(e)
    }
}
