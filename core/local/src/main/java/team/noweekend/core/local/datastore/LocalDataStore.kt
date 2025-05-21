package team.noweekend.core.local.datastore

interface LocalDataStore {
    suspend fun getAccessToken(): String
    suspend fun setAccessToken(token: String)
    suspend fun getRefreshToken(): String
    suspend fun setRefreshToken(token: String)
}
