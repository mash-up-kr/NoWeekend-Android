package team.noweekend.core.common.ui.schedule.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.common.ui.schedule.model.FrequentSchedule

internal class PreviewFrequentSchedulesProvider :
    PreviewParameterProvider<ImmutableList<FrequentSchedule>> {
    override val values: Sequence<ImmutableList<FrequentSchedule>>
        get() = sequenceOf(
            persistentListOf(
                FrequentSchedule(
                    title = "점심 식사 약속",
                    isSelected = true,
                ),
                FrequentSchedule(
                    title = "회의 참석",
                    isSelected = false,
                ),
                FrequentSchedule(
                    title = "회의 참석",
                    isSelected = false,
                ),
                FrequentSchedule(
                    title = "산책",
                    isSelected = false,
                ),
                FrequentSchedule(
                    title = "점심 식사 약속",
                    isSelected = true,
                ),
                FrequentSchedule(
                    title = "산책",
                    isSelected = false,
                ),
                FrequentSchedule(
                    title = "헬스장 운동",
                    isSelected = true,
                ),
                FrequentSchedule(
                    title = "헬스장 운동",
                    isSelected = true,
                ),

                FrequentSchedule(
                    title = "회의 참석",
                    isSelected = false,
                ),
                FrequentSchedule(
                    title = "점심 식사 약속",
                    isSelected = true,
                ),
                FrequentSchedule(
                    title = "회의 참석",
                    isSelected = false,
                ),
                FrequentSchedule(
                    title = "점심 식사 약속",
                    isSelected = true,
                ),

            ),
        )
}
