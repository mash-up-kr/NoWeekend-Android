package team.noweekend.data.repository

import team.noweekend.core.domain.repository.AuthRepository
import team.noweekend.core.local.datastore.LocalDataStore
import team.noweekend.core.remote.provider.TokenProvider
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val localDataStore: LocalDataStore,
    private val tokenProvider: TokenProvider,
) : AuthRepository {

    override suspend fun getAccessToken(): String {
        val token = localDataStore.getAccessToken()
        tokenProvider.updateAccessToken(token = token)
        return token
    }

    override suspend fun setAccessToken(token: String) {
        return localDataStore
            .setAccessToken(token)
            .also {
                tokenProvider.updateAccessToken(token)
            }
    }

    override suspend fun getRefreshToken(): String {
        return localDataStore.getRefreshToken()
    }

    override suspend fun setRefreshToken(token: String) {
        return localDataStore
            .setRefreshToken(token)
            .also {
                tokenProvider.updateRefreshToken(token)
            }
    }
}
