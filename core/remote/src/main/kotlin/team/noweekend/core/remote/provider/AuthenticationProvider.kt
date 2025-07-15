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
        val accessToken = tokenProvider.tokenFlow.first().access
        request.headers.append(HttpHeaders.Authorization, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ2MS0yMDI1MDcxNTAxMjkyMC03OTk1MTk4ZjViODc0OTBlYjU4YzBkZTBiNjBmYjRlZSIsImlzcyI6Imh0dHBzOi8vd3d3Lm5vd2Vla2VuZC5jb20iLCJpYXQiOjE3NTI1MTA1NjAsImV4cCI6MTc1MjU5Njk2MH0.4cf2wWftC94j6bnT_4gBwOqcwJU5IdUWklCtsgcTVM8")
    }

    override fun isApplicable(auth: HttpAuthHeader): Boolean {
        return auth is HttpAuthHeader.Single && auth.authScheme.equals("bearer", ignoreCase = true)
    }
}
