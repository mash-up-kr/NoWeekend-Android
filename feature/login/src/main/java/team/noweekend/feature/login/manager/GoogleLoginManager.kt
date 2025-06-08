package team.noweekend.feature.login.manager

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import team.noweekend.feature.login.BuildConfig
import team.noweekend.feature.login.model.UserAuthInfo
import javax.inject.Inject

class GoogleLoginManager @Inject constructor(
    private val context: Context,
    private val credentialManager: CredentialManager
) {
    suspend fun login(): Result<UserAuthInfo> {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .setAutoSelectEnabled(true)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        return runCatching {
            val result = credentialManager.getCredential(
                request = request,
                context = context
            )
            handleSignIn(result)
        }
    }

    suspend fun logout(): Result<Unit> {
        return runCatching {
            credentialManager.clearCredentialState(
                ClearCredentialStateRequest()
            )
        }
    }

    private fun handleSignIn(result: GetCredentialResponse): UserAuthInfo {
        return when (val credential = result.credential) {
            is CustomCredential -> {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                UserAuthInfo.toModel(googleIdTokenCredential)
            }

            else -> {
                // TODO: 향후 예외 처리 추가
                throw Exception()
            }
        }
    }
}
