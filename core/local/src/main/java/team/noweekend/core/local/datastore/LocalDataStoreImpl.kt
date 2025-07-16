package team.noweekend.core.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class LocalDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : LocalDataStore {
    override suspend fun getAccessToken(): String {
        return dataStore.getOrThrow(key = ACCESS_TOKEN_KEY, errorMessage = "Access token not found")
    }

    override suspend fun setAccessToken(token: String) {
        dataStore.set(key = ACCESS_TOKEN_KEY, value = token)
    }

    override suspend fun getRefreshToken(): String {
        return dataStore.getOrThrow(REFRESH_TOKEN_KEY, "Refresh token not found")
    }

    override suspend fun setRefreshToken(token: String) {
        dataStore.set(key = REFRESH_TOKEN_KEY, value = token)
    }

    override suspend fun getOnboardIsFinished(): Boolean {
        return dataStore.getOrThrow(ONBOARD_IS_FINISHED_KEY, "Onboard is finished key not found").toBoolean()
    }

    override suspend fun setOnboardIsFinished(isFinished: Boolean) {
        dataStore.set(key = ONBOARD_IS_FINISHED_KEY, value = isFinished.toString())
    }

    companion object {
        private const val ACCESS_TOKEN_KEY = "access_token_prefs"
        private const val REFRESH_TOKEN_KEY = "refresh_token_prefs"
        private const val ONBOARD_IS_FINISHED_KEY = "onboard_is_finished_prefs"
    }
}

private suspend fun DataStore<Preferences>.getOrThrow(
    key: String,
    errorMessage: String,
): String = data.first()[stringPreferencesKey(key)] ?: throw NoSuchElementException(errorMessage)

private suspend fun DataStore<Preferences>.set(
    key: String,
    value: String,
) {
    edit { it[stringPreferencesKey(key)] = value }
}
