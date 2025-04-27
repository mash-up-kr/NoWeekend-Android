package team.noweekend.feature.sample.navigator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.noweekend.core.navigator.feature.SampleNavigator
import team.noweekend.feature.sample.navigator.SampleNavigatorImpl

@Module
@InstallIn(ActivityComponent::class)
internal abstract class SampleNavigatorModule {
    @Binds
    @ActivityScoped
    abstract fun bindSampleNavigator(impl: SampleNavigatorImpl): SampleNavigator

}
