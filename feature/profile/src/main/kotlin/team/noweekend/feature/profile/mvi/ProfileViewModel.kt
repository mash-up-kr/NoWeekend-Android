package team.noweekend.feature.profile.mvi

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.domain.usecase.GetUserProfileUseCase
import team.noweekend.feature.profile.model.Menu
import team.noweekend.feature.profile.model.WebLinkMenu
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserProfileUseCase: GetUserProfileUseCase,
) : MVIViewModel<ProfileIntent, ProfileSideEffect, ProfileUiState>(
    savedStateHandle = savedStateHandle,
) {

    init {
        getUserProfile()
    }

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

    private fun getUserProfile() = execute {
        getUserProfileUseCase.invoke()
            .onSuccess {
                reduce { copy(user = it) }
            }
            .onFailure {
                Log.d("logtag", "$it")
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
