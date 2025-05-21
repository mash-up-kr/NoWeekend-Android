package team.noweekend.core.remote.base

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal suspend inline fun <reified T> safeApiCall(
    crossinline apiCall: suspend () -> T,
): T = withContext(Dispatchers.IO) { apiCall() }

internal suspend inline fun <reified T> HttpClient.getApiCall(
    path: String,
    queries: Map<String, String>? = null,
): T = safeApiCall {
    this.get(path) {
        queries?.forEach { (key, value) ->
            parameter(key, value)
        }
    }.body()
}

internal suspend inline fun <reified T> HttpClient.postApiCall(
    path: String,
    body: Any? = null,
    queries: Map<String, String>? = null,
): T = safeApiCall {
    this.post(path) {
        body?.let { setBody(body) }
        queries?.forEach { (key, value) ->
            parameter(key, value)
        }
    }.body()
}

internal suspend inline fun <reified T> HttpClient.putApiCall(
    path: String,
    body: Any? = null,
    queries: Map<String, String>? = null,
): T = safeApiCall {
    this.put(path) {
        body?.let { setBody(body) }
        queries?.forEach { (key, value) ->
            parameter(key, value)
        }
    }.body()
}