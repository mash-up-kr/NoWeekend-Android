package team.noweekend.core.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.noweekend.core.remote.api.schedule.ScheduleApi
import team.noweekend.core.remote.api.schedule.ScheduleApiImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkApiModule {

    @Binds
    @Singleton
    fun bindScheduleApi(scheduleApiImpl: ScheduleApiImpl): ScheduleApi
}
