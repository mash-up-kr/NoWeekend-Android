package team.noweekend.feature.create.vacation.recommend.model

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.MONTH_DATE_WITH_DAY_OF_WEEK_PATTERN
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.kotlin.extension.toFormattedString
import team.noweekend.core.resource.NWKDrawableResource

@Stable
data class RecommendedVacationUiModel(
    val vacationType: RecommendVacationType,
    val recommendedContent: String,
    val recommendedStartDate: LocalDate,
    val recommendedEndDate: LocalDate,
) {
    val displayRecommendedDate: String = formatDisplayDate(recommendedStartDate, recommendedEndDate)

    private fun formatDisplayDate(startLocalDate: LocalDate, endLocalDate: LocalDate?): String {
        val startDisplayDate: String =
            startLocalDate.toFormattedString(LocalDate.MONTH_DATE_WITH_DAY_OF_WEEK_PATTERN)
        val endDisplayDate: String? = endLocalDate?.toFormattedString(LocalDate.MONTH_DATE_WITH_DAY_OF_WEEK_PATTERN)

        return if (endDisplayDate.isNullOrEmpty()) {
            "$startDisplayDate ~ $endDisplayDate"
        } else {
            startDisplayDate
        }
    }

    companion object {
        val INITIAL_DATA: RecommendedVacationUiModel = RecommendedVacationUiModel(
            recommendedContent = "",
            vacationType = RecommendVacationType.UNDEFINED,
            recommendedStartDate = LocalDate.now(),
            recommendedEndDate = LocalDate.now(),
        )
    }
}

/**
 * 추천 휴가 종류
 * - [RecommendVacationType.ABROAD] : 해외여행
 * - [RecommendVacationType.LOCAL] : 국내여행
 * - [RecommendVacationType.HOME] : 집
 * - [RecommendVacationType.GOOD] : 가성비 휴가
 */
enum class RecommendVacationType {
    ABROAD,
    LOCAL,
    HOME,
    GOOD,
    UNDEFINED,
    ;

    companion object {
        fun RecommendVacationType.getRecommendedVacationImageResource(): Int {
            return when (this) {
                ABROAD -> NWKDrawableResource.ToasterTrip
                LOCAL -> NWKDrawableResource.ToasterTrain
                HOME -> NWKDrawableResource.ToasterHome
                GOOD -> NWKDrawableResource.ToasterGood
                UNDEFINED -> NWKDrawableResource.ToasterDefault
            }
        }
    }
}
