package team.noweekend.feature.main.navigator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.noweekend.core.navigator.feature.MainNavigator
import team.noweekend.feature.main.navigator.MainNavigatorImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class MainNavigatorModule {

    @Binds
    @ActivityScoped
    abstract fun bindMainNavigator(
        impl: MainNavigatorImpl,
    ): MainNavigator
}
