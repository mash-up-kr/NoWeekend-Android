package team.noweekend.core.remote.provider

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import team.noweekend.core.remote.model.Token
import javax.inject.Inject

internal class TokenProviderImpl @Inject constructor() : TokenProvider {
    
    private val _tokenFlow = MutableStateFlow<Token>(Token.INITIAL_STATE)
    override val tokenFlow: StateFlow<Token> = _tokenFlow.asStateFlow()
    
    
    override suspend fun updateAccessToken(token: String) {
        _tokenFlow.update {
            it.copy(access = token)
        }
    }
    
    override suspend fun updateRefreshToken(token: String) {
        _tokenFlow.update {
            it.copy(refresh = token)
        }
    }
}
