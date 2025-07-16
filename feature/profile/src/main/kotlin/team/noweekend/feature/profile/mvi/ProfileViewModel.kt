package team.noweekend.feature.profile.mvi

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.feature.profile.model.Menu
import team.noweekend.feature.profile.model.WebLinkMenu
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<ProfileIntent, ProfileSideEffect, ProfileUiState>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): ProfileUiState {
        return ProfileUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {}

    override suspend fun handleIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.ClickMenu -> handleMenuTab(intent.menu)
            else -> {}
        }
    }

    private fun handleMenuTab(menu: Menu) {
        when (menu) {
            is WebLinkMenu -> navigateToExternalWebBrowser(menu.url)
            else -> {}
        }
    }

    private fun navigateToExternalWebBrowser(url: String) = execute {
        postSideEffect(ProfileSideEffect.NavigateToExternalWebBrowser(url))
    }
}
