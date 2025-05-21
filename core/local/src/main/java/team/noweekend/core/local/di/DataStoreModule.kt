package team.noweekend.core.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import team.noweekend.core.local.datastore.LocalDataStore
import team.noweekend.core.local.datastore.LocalDataStoreImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataStoreModule {

    @Singleton
    @Binds
    fun bindLocalDataStore(localDataStore: LocalDataStoreImpl): LocalDataStore
}

@Module
@InstallIn(SingletonComponent::class)
internal class DataStoreProvider {

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(PREFERENCES) }
        )
    }

    companion object {
        private const val PREFERENCES = "user_auth_token"
    }
}
