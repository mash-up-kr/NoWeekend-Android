package team.noweekend.core.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import team.noweekend.core.remote.BuildConfig
import team.noweekend.core.remote.provider.AuthenticationProvider
import team.noweekend.core.remote.qulifier.BaseUrl
import team.noweekend.core.remote.qulifier.BasicClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {
    
    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String {
        return "api.github.com"
    }
    
    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }
    
    @Provides
    @Singleton
    @BasicClient
    fun provideHttpClient(
        json: Json,
        @BaseUrl baseUrl: String,
        authProvider: AuthenticationProvider,
    ): HttpClient {
        return HttpClient(Android) {
            expectSuccess = true
            install(ContentNegotiation) { json(json) }
            install(DefaultRequest) {
                contentType(ContentType.Application.Json)
                url {
                    host = baseUrl
                    protocol = URLProtocol.HTTPS
                }
            }
            install(Resources)
            install(HttpTimeout) {
                requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
                connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
                socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
            }
            install(Auth) {
                providers.add(authProvider)
            }
        }
    }
    
    companion object {
        private const val REQUEST_TIMEOUT_MILLIS = 10_000L
        private const val CONNECT_TIMEOUT_MILLIS = 10_000L
        private const val SOCKET_TIMEOUT_MILLIS = 10_000L
    }
}