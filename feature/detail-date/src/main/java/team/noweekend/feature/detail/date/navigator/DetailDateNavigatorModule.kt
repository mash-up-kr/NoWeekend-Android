package team.noweekend.feature.detail.date.navigator

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.noweekend.core.navigator.feature.DetailDateNavigator

@Module
@InstallIn(ActivityComponent::class)
abstract class DetailDateNavigatorModule {

    @Binds
    @ActivityScoped
    abstract fun bindDetailDateNavigator(detailDateNavigatorImpl: DetailDateNavigatorImpl): DetailDateNavigator
}
