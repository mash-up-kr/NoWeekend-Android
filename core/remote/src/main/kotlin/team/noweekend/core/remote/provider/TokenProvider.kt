package team.noweekend.core.remote.provider

import kotlinx.coroutines.flow.Flow

interface TokenProvider {
    val accessTokenFlow: Flow<String>
    val refreshTokenFlow: Flow<String>
    suspend fun updateAccessToken(token: String)
    suspend fun updateRefreshToken(token: String)
}