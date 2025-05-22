package team.noweekend.core.remote.provider

import io.ktor.client.plugins.auth.AuthProvider
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.auth.HttpAuthHeader
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton
import io.ktor.http.HttpHeaders

@Singleton
internal class AuthenticationProvider @Inject constructor(
    private val tokenProvider: TokenProvider,
) : AuthProvider {
    override val sendWithoutRequest: Boolean
        get() = true

    override suspend fun addRequestHeaders(
        request: HttpRequestBuilder,
        authHeader: HttpAuthHeader?,
    ) {
        val accessToken = tokenProvider.accessTokenFlow.first()
        request.headers.append(HttpHeaders.Authorization, "Bearer $accessToken")
    }

    override fun isApplicable(auth: HttpAuthHeader): Boolean {
        return auth is HttpAuthHeader.Single && auth.authScheme.equals("bearer", ignoreCase = true)
    }
}