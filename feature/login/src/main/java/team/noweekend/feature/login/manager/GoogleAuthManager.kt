package team.noweekend.feature.login.manager

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.gms.auth.api.identity.AuthorizationRequest
import com.google.android.gms.auth.api.identity.AuthorizationResult
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import team.noweekend.feature.login.BuildConfig
import javax.inject.Inject

class GoogleAuthManager @Inject constructor(
    private val authorizationRequest: AuthorizationRequest,
) {
    private lateinit var credentialManager: CredentialManager

    fun startGoogleLogin(context: Context): Flow<AuthorizationResult> {
        credentialManager = CredentialManager.create(context)

        val googleSignInOption = GetSignInWithGoogleOption
            .Builder(BuildConfig.GOOGLE_CLIENT_ID)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleSignInOption)
            .build()

        return handleGoogleSignIn(request, context)
    }

    private fun handleGoogleSignIn(
        request: GetCredentialRequest,
        context: Context,
    ): Flow<AuthorizationResult> {
        return callbackFlow {
            val response = credentialManager.getCredential(
                request = request,
                context = context,
            )
            val credential = response.credential
            when (credential) {
                is CustomCredential -> {
                    Identity.getAuthorizationClient(context)
                        .authorize(authorizationRequest)
                        .addOnSuccessListener { authorizationResult ->
                            trySend(authorizationResult)
                        }
                        .addOnFailureListener { e ->
                            close(e)
                        }
                }
            }
            awaitClose()
        }
    }
}
