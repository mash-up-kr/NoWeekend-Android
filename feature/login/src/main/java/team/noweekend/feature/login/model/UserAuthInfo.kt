package team.noweekend.feature.login.model

import androidx.compose.runtime.Stable
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

@Stable
data class UserAuthInfo(
    val nickname: String?,
    val authToken: String?
) {
    companion object {
        fun toModel(googleIdTokenCredential: GoogleIdTokenCredential): UserAuthInfo {
            return UserAuthInfo(
                nickname = googleIdTokenCredential.displayName,
                authToken = googleIdTokenCredential.idToken
            )
        }
    }
}
