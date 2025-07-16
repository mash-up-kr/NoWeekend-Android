package team.noweekend.core.remote.provider

import kotlinx.coroutines.flow.Flow
import team.noweekend.core.remote.model.user.Token

interface TokenProvider {
    val tokenFlow: Flow<Token>
    suspend fun updateAccessToken(token: String)
    suspend fun updateRefreshToken(token: String)
}
