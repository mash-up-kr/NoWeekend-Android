package team.noweekend.feature.create.vacation.navigator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.noweekend.core.navigator.feature.CreateVacationNavigator
import team.noweekend.feature.create.vacation.navigator.CreateVacationNavigatorImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class CreateVacationNavigatorModule {

    @Binds
    @ActivityScoped
    abstract fun bindCreateVacationNavigator(impl: CreateVacationNavigatorImpl): CreateVacationNavigator
}
