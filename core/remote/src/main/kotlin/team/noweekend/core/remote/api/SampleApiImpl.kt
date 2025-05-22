package team.noweekend.core.remote.api

import io.ktor.client.HttpClient
import team.noweekend.core.remote.base.getApiCall
import team.noweekend.core.remote.qulifier.BasicClient
import javax.inject.Inject

internal class SampleApiImpl @Inject constructor(
    @BasicClient val client: HttpClient,
) : SampleApi {
    override suspend fun getApiCall() {
        return client.getApiCall(path = "search/users", queries = mapOf("q" to ""))
    }
}