import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonNull
import kotlinx.serialization.json.jsonObject
import team.noweekend.core.remote.base.NWKApiException
import team.noweekend.core.remote.base.NWKErrorResponse

object NWKApiErrorParser {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun parse(e: Throwable): Throwable {
        return when (e) {
            is ResponseException -> {
                val response = e.response
                val error = parseErrorResponse(response)
                NWKApiException(errorResponse = error)
            }

            else -> e
        }
    }

    private suspend fun parseErrorResponse(response: HttpResponse): NWKErrorResponse? {
        return try {
            val responseString = response.bodyAsText()
            val root = json.parseToJsonElement(responseString).jsonObject

            val errorElement = root["error"]

            errorElement?.let {
                json.decodeFromJsonElement<NWKErrorResponse>(it)
            }
        } catch (e: Exception) {
            null
        }
    }
}
