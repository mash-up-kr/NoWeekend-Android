package team.noweekend.feature.login.di

import com.google.android.gms.auth.api.identity.AuthorizationRequest
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import team.noweekend.feature.login.BuildConfig
import team.noweekend.feature.login.manager.GoogleAuthManager

@InstallIn(ActivityRetainedComponent::class)
@Module
internal class GoogleAuthModule {

    @Provides
    @ActivityRetainedScoped
    fun provideGoogleLoginManager(
        authorizationRequest: AuthorizationRequest
    ): GoogleAuthManager = GoogleAuthManager(authorizationRequest)

    @Provides
    @ActivityRetainedScoped
    fun provideAuthScopeList(): List<Scope> {
        return listOf(
            Scope(Scopes.EMAIL),
            Scope(Scopes.PROFILE),
            Scope("https://www.googleapis.com/auth/calendar.readonly")
        )
    }

    @Provides
    @ActivityRetainedScoped
    fun provideGoogleAuthRequest(
        requestScope: List<Scope>
    ): AuthorizationRequest {
        return AuthorizationRequest.builder()
            .setRequestedScopes(requestScope)
            .requestOfflineAccess(BuildConfig.GOOGLE_CLIENT_ID)
            .build()
    }
}
