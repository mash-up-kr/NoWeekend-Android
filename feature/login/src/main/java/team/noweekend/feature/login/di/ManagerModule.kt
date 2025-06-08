package team.noweekend.feature.login.di

import android.content.Context
import androidx.credentials.CredentialManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import team.noweekend.feature.login.manager.GoogleLoginManager

@InstallIn(ActivityRetainedComponent::class)
@Module
internal class ManagerModule {

    @Provides
    @ActivityRetainedScoped
    fun provideCredentialManager(
        @ApplicationContext context: Context
    ): CredentialManager = CredentialManager.create(context)

    @Provides
    @ActivityRetainedScoped
    fun provideGoogleLoginManager(
        credentialManager: CredentialManager,
        @ApplicationContext context: Context
    ): GoogleLoginManager = GoogleLoginManager(context, credentialManager)
}
