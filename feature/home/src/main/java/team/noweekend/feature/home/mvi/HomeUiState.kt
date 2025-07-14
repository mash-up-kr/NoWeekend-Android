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
            createVacationStatus = CreateVacationStatus.Default(6),
        )
    }
}

/**
 * 휴가 굽기 UI 노출 상태
 * - [CreateVacationStatus.Default] : 기본 상태
 * - [CreateVacationStatus.InProgress] : 휴가 굽는 중
 * - [CreateVacationStatus.Complete] : 휴가 굽기 완료
 * - [CreateVacationStatus.Done] : 생성된 휴가 할일 추가 완료
 */
sealed interface CreateVacationStatus {
    val messageResourceId: Int
    val imageResourceId: Int
    val buttonText: Int

    data class Default(
        val maximumVacation: Int,
    ) : CreateVacationStatus {
        override val messageResourceId: Int = NWKStringResource.HomeToastDefaultTitle
        override val imageResourceId: Int = NWKDrawableResource.MainToasterDefault
        override val buttonText: Int = NWKStringResource.HomeCreateVacationDefaultButtonText
    }

    data object InProgress : CreateVacationStatus {
        override val messageResourceId: Int = NWKStringResource.HomeToastProgressTitle
        override val imageResourceId: Int = NWKDrawableResource.MainToasterProgress
        override val buttonText: Int = NWKStringResource.HomeCreateVacationProgressButtonText
    }

    data object Complete : CreateVacationStatus {
        override val messageResourceId: Int = NWKStringResource.HomeToastCompleteTitle
        override val imageResourceId: Int = NWKDrawableResource.MainToasterDefault
        override val buttonText: Int = NWKStringResource.HomeCreateVacationCompleteButtonText
    }

    data object Done : CreateVacationStatus {
        override val messageResourceId: Int = NWKStringResource.HomeToastDoneTitle
        override val imageResourceId: Int = NWKDrawableResource.MainToasterDefault
        override val buttonText: Int = NWKStringResource.HomeCreateVacationDoneButtonText
    }
}
