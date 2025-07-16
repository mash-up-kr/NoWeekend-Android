package team.noweekend.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.noweekend.core.domain.repository.AuthRepository
import team.noweekend.core.domain.repository.HolidayRepository
import team.noweekend.core.domain.repository.LocationRepository
import team.noweekend.core.domain.repository.LoginRepository
import team.noweekend.core.domain.repository.OnboardRepository
import team.noweekend.core.domain.repository.RecommendRepository
import team.noweekend.core.domain.repository.ScheduleRepository
import team.noweekend.core.domain.repository.UserRepository
import team.noweekend.data.repository.AuthRepositoryImpl
import team.noweekend.data.repository.HolidayRepositoryImpl
import team.noweekend.data.repository.LocationRepositoryImpl
import team.noweekend.data.repository.LoginRepositoryImpl
import team.noweekend.data.repository.OnboardRepositoryImpl
import team.noweekend.data.repository.RecommendRepositoryImpl
import team.noweekend.data.repository.ScheduleRepositoryImpl
import team.noweekend.data.repository.UserRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Singleton
    @Binds
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    fun bindScheduleRepository(scheduleRepositoryImpl: ScheduleRepositoryImpl): ScheduleRepository

    @Singleton
    @Binds
    fun bindHolidayRepository(impl: HolidayRepositoryImpl): HolidayRepository

    @Singleton
    @Binds
    fun bindRecommendRepository(impl: RecommendRepositoryImpl): RecommendRepository

    @Singleton
    @Binds
    fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository

    @Singleton
    @Binds
    fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Singleton
    @Binds
    fun bindOnboardingRepository(onboardRepositoryImpl: OnboardRepositoryImpl): OnboardRepository

    @Singleton
    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}
