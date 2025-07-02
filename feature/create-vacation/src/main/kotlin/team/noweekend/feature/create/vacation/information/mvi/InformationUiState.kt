package team.noweekend.feature.create.vacation.information.mvi

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import team.noweekend.core.common.android.mvi.UiState
import team.noweekend.feature.create.vacation.information.model.InformationRadioGroupUiModel

@Stable
data class InformationUiState(
    val isLoading: Boolean,
    val information: ImmutableMap<Int, ImmutableList<InformationRadioGroupUiModel>>,
) : UiState {
    val isButtonEnabled: Boolean = false

    companion object {
        val INITIAL_STATE: InformationUiState = InformationUiState(
            isLoading = false,
            information = persistentMapOf(
                Pair(
                    1,
                    persistentListOf(
                        InformationRadioGroupUiModel(
                            text = "계획형",
                            isSelected = false,
                        ),
                        InformationRadioGroupUiModel(
                            text = "즉흥 자유형",
                            isSelected = false,
                        ),
                    ),
                ),
                Pair(
                    2,
                    persistentListOf(
                        InformationRadioGroupUiModel(
                            text = "야외 활동",
                            isSelected = false,
                        ),
                        InformationRadioGroupUiModel(
                            text = "집콕",
                            isSelected = false,
                        ),
                    ),
                ),
                Pair(
                    3,
                    persistentListOf(
                        InformationRadioGroupUiModel(
                            text = "휴식",
                            isSelected = false,
                        ),
                        InformationRadioGroupUiModel(
                            text = "자기계발",
                            isSelected = false,
                        ),
                    ),
                ),
                Pair(
                    4,
                    persistentListOf(
                        InformationRadioGroupUiModel(
                            text = "음식",
                            isSelected = false,
                        ),
                        InformationRadioGroupUiModel(
                            text = "관광",
                            isSelected = false,
                        ),
                    ),
                ),
            ),
        )

        val DUMMY_STATE: InformationUiState = InformationUiState(
            isLoading = false,
            information = persistentMapOf(
                Pair(
                    1,
                    persistentListOf(
                        InformationRadioGroupUiModel(
                            text = "계획형",
                            isSelected = false,
                        ),
                        InformationRadioGroupUiModel(
                            text = "즉흥 자유형",
                            isSelected = false,
                        ),
                    ),
                ),
                Pair(
                    2,
                    persistentListOf(
                        InformationRadioGroupUiModel(
                            text = "야외 활동",
                            isSelected = false,
                        ),
                        InformationRadioGroupUiModel(
                            text = "집콕",
                            isSelected = false,
                        ),
                    ),
                ),
                Pair(
                    3,
                    persistentListOf(
                        InformationRadioGroupUiModel(
                            text = "휴식",
                            isSelected = false,
                        ),
                        InformationRadioGroupUiModel(
                            text = "자기계발",
                            isSelected = false,
                        ),
                    ),
                ),
                Pair(
                    4,
                    persistentListOf(
                        InformationRadioGroupUiModel(
                            text = "음식",
                            isSelected = false,
                        ),
                        InformationRadioGroupUiModel(
                            text = "관광",
                            isSelected = false,
                        ),
                    ),
                ),
            ),
        )
    }
}
