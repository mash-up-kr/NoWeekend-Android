package team.noweekend.core.domain.repository

interface AuthRepository {
    suspend fun getAccessToken(): String
    suspend fun setAccessToken(token: String)
    suspend fun getRefreshToken(): String
    suspend fun setRefreshToken(token: String)
}
