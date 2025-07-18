package team.noweekend.feature.addtask.navigator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.noweekend.core.navigator.feature.AddTaskNavigator
import team.noweekend.feature.addtask.navigator.AddTaskNavigatorImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class AddTaskNavigatorModule {

    @Binds
    @ActivityScoped
    abstract fun bindAddTaskNavigator(impl: AddTaskNavigatorImpl): AddTaskNavigator
}
