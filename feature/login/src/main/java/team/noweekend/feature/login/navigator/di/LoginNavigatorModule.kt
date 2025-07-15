package team.noweekend.feature.login.navigator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.noweekend.core.navigator.feature.LoginNavigator
import team.noweekend.feature.login.navigator.LoginNavigatorImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class LoginNavigatorModule {
    @Binds
    @ActivityScoped
    abstract fun bindLoginNavigator(impl: LoginNavigatorImpl): LoginNavigator
}
