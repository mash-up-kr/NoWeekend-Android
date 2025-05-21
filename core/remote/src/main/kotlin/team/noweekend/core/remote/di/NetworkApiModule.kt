package team.noweekend.core.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.noweekend.core.remote.api.SampleApi
import team.noweekend.core.remote.api.SampleApiImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkApiModule {
    @Binds
    @Singleton
    fun bindSampleApi(sampleApiImpl: SampleApiImpl): SampleApi
}