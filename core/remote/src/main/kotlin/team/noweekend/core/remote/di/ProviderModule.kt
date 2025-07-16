package team.noweekend.core.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.noweekend.core.remote.provider.TokenProvider
import team.noweekend.core.remote.provider.TokenProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ProviderModule {
    @Binds
    @Singleton
    fun bindTokenProvider(tokenProviderImpl: TokenProviderImpl): TokenProvider
}