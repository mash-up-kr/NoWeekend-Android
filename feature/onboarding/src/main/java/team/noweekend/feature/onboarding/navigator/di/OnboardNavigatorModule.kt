package team.noweekend.feature.onboarding.navigator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.noweekend.core.navigator.feature.OnboardNavigator
import team.noweekend.feature.onboarding.navigator.OnboardNavigatorImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class OnboardNavigatorModule {

    @Binds
    @ActivityScoped
    abstract fun bindOnboardNavigator(impl: OnboardNavigatorImpl): OnboardNavigator
}
