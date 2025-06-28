package team.noweekend.core.navigator.delegate.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import team.noweekend.core.navigator.delegate.HomeNavigationDelegate
import team.noweekend.core.navigator.feature.CreateVacationNavigator

@Module
@InstallIn(ActivityComponent::class)
internal class NavigationDelegateModule {

    @Provides
    @ActivityScoped
    fun provideHomeNavigationDelegate(
        @ActivityContext context: Context,
        createVacationNavigator: CreateVacationNavigator,
    ): HomeNavigationDelegate = HomeNavigationDelegate(context, createVacationNavigator)
}
