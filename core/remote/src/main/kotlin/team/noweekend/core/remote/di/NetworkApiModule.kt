package team.noweekend.core.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.noweekend.core.remote.api.holiday.HolidayApi
import team.noweekend.core.remote.api.holiday.HolidayApiImpl
import team.noweekend.core.remote.api.location.LocationApi
import team.noweekend.core.remote.api.location.LocationApiImpl
import team.noweekend.core.remote.api.login.LoginApi
import team.noweekend.core.remote.api.login.LoginApiImpl
import team.noweekend.core.remote.api.recommend.RecommendApi
import team.noweekend.core.remote.api.recommend.RecommendApiImpl
import team.noweekend.core.remote.api.onboard.OnboardApi
import team.noweekend.core.remote.api.onboard.OnboardApiImpl
import team.noweekend.core.remote.api.schedule.ScheduleApi
import team.noweekend.core.remote.api.schedule.ScheduleApiImpl
import team.noweekend.core.remote.api.user.UserApi
import team.noweekend.core.remote.api.user.UserApiImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkApiModule {

    @Binds
    @Singleton
    fun bindScheduleApi(scheduleApiImpl: ScheduleApiImpl): ScheduleApi

    @Binds
    @Singleton
    fun bindLoginApi(loginApiImpl: LoginApiImpl): LoginApi

    @Binds
    @Singleton
    fun bindHolidayApi(impl: HolidayApiImpl): HolidayApi

    @Binds
    @Singleton
    fun bindRecommendApi(impl: RecommendApiImpl): RecommendApi

    @Binds
    @Singleton
    fun bindLocationApi(impl: LocationApiImpl): LocationApi

    @Binds
    @Singleton
    fun bindUserApi(impl: UserApiImpl): UserApi

    @Binds
    @Singleton
    fun bindOnboardingApi(onboardingApiImpl: OnboardApiImpl): OnboardApi
}
 // 하 시발 이거 해야 되는데 ㅈ됐다
