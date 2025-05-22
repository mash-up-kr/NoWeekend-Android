package team.noweekend.core.remote.provider

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

internal class TokenProviderImpl @Inject constructor() : TokenProvider {
    
    private val _accessTokenFlow = MutableStateFlow<String>("")
    override val accessTokenFlow: StateFlow<String> = _accessTokenFlow
    
    private val _refreshTokenFlow = MutableStateFlow<String>("")
    override val refreshTokenFlow: StateFlow<String> = _refreshTokenFlow
    
    override suspend fun updateAccessToken(token: String) {
        _accessTokenFlow.emit(token)
    }
    
    override suspend fun updateRefreshToken(token: String) {
        _refreshTokenFlow.emit(token)
    }
}