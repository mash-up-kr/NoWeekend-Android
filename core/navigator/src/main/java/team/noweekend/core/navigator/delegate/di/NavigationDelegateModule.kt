package team.noweekend.core.navigator.delegate.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import team.noweekend.core.navigator.delegate.HomeNavigationDelegate
import team.noweekend.core.navigator.feature.sample.SampleNavigator

@Module
@InstallIn(ActivityComponent::class)
internal class NavigationDelegateModule {

    @Provides
    @ActivityScoped
    fun provideHomeNavigationDelegate(
        @ActivityContext context: Context,
        sampleNavigator: SampleNavigator,
    ): HomeNavigationDelegate = HomeNavigationDelegate(context, sampleNavigator)
}