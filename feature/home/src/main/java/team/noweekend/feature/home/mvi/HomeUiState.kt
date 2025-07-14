package team.noweekend.feature.home.mvi

import androidx.compose.runtime.Stable
import team.noweekend.core.common.android.mvi.UiState
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource

@Stable
data class HomeUiState(
    val isLoading: Boolean,
    val createVacationStatus: CreateVacationStatus,
) : UiState {

    companion object {
        val INITIAL_STATE: HomeUiState = HomeUiState(
            isLoading = false,
            createVacationStatus = CreateVacationStatus.DEFAULT,
        )
    }
}

enum class CreateVacationStatus(
    val messageResourceId: Int,
    val imageResourceId: Int,
) {
    DEFAULT(
        messageResourceId = NWKStringResource.HomeToastDefaultTitle,
        imageResourceId = NWKDrawableResource.MainToasterDefault,
    ),
    IN_PROGRESS(
        messageResourceId = NWKStringResource.HomeToastProgressTitle,
        imageResourceId = NWKDrawableResource.MainToasterProgress,
    ),
    COMPLETE(
        messageResourceId = NWKStringResource.HomeToastCompleteTitle,
        imageResourceId = NWKDrawableResource.MainToasterDefault,
    )
}
