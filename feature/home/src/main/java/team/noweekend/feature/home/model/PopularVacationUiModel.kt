package team.noweekend.feature.home.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.MONTH_DATE_WITH_DAY_OF_WEEK_PATTERN
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.kotlin.extension.toFormattedString
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.model.vacation.SandwichRecommendVacation
import team.noweekend.core.model.vacation.VacationType
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource

@Stable
data class PopularVacationUiModel(
    val vacationType: VacationType,
    val startLocalDate: LocalDate,
    val endLocalDate: LocalDate?,
) {
    val imageResourceId: Int = vacationType.getImageResource()
    val displayDate: String = formatDisplayDate(startLocalDate, endLocalDate)

    private fun VacationType.getImageResource(): Int {
        return when (this) {
            VacationType.HOLIDAY_EXIST -> NWKDrawableResource.Korea
            VacationType.HOLIDAY_EXIST_NOT -> NWKDrawableResource.Korea
            VacationType.INCLUDE_MONDAY -> NWKDrawableResource.Star
            VacationType.INCLUDE_FRIDAY -> NWKDrawableResource.Star
            VacationType.INCLUDE_WEEKEND -> NWKDrawableResource.Sandwich
            VacationType.BIRTHDAY_EXIST -> NWKDrawableResource.Cake
        }
    }

    private fun formatDisplayDate(startLocalDate: LocalDate, endLocalDate: LocalDate?): String {
        val startDisplayDate: String =
            startLocalDate.toFormattedString(LocalDate.MONTH_DATE_WITH_DAY_OF_WEEK_PATTERN)
        val endDisplayDate: String? =
            endLocalDate?.toFormattedString(LocalDate.MONTH_DATE_WITH_DAY_OF_WEEK_PATTERN)

        return if (endDisplayDate.isNullOrEmpty().not()) {
            "$startDisplayDate ~ $endDisplayDate"
        } else {
            startDisplayDate
        }
    }

    companion object {
        val INITIAL_DATA: PopularVacationUiModel = PopularVacationUiModel(
            vacationType = VacationType.HOLIDAY_EXIST,
            startLocalDate = LocalDate.now(),
            endLocalDate = null,
        )

        @Composable
        fun VacationType.getStyledDescription(): AnnotatedString {
            return when (this) {
                VacationType.HOLIDAY_EXIST -> {
                    buildAnnotatedString {
                        append(stringResource(NWKStringResource.HomePopularVacationHolidayExistSpan1))
                        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
                            append(stringResource(NWKStringResource.HomePopularVacationHolidayExistSpan2))
                        }
                        append(stringResource(NWKStringResource.HomePopularVacationHolidayExistSpan3))
                        append(stringResource(NWKStringResource.HomePopularVacationHolidayExistSpan4))
                    }
                }

                VacationType.HOLIDAY_EXIST_NOT -> {
                    buildAnnotatedString {
                        append(stringResource(NWKStringResource.HomePopularVacationHolidayExistNotSpan1))
                    }
                }

                VacationType.INCLUDE_MONDAY -> {
                    buildAnnotatedString {
                        append(stringResource(NWKStringResource.HomePopularVacationIncludeMondaySpan1))
                        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
                            append(stringResource(NWKStringResource.HomePopularVacationIncludeMondaySpan2, 3))
                        }
                        append(stringResource(NWKStringResource.HomePopularVacationIncludeMondaySpan3))
                    }
                }

                VacationType.INCLUDE_FRIDAY -> {
                    buildAnnotatedString {
                        append(stringResource(NWKStringResource.HomePopularVacationIncludeFridaySpan1))
                        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
                            append(stringResource(NWKStringResource.HomePopularVacationIncludeFridaySpan2, 3))
                        }
                        append(stringResource(NWKStringResource.HomePopularVacationIncludeFridaySpan3))
                    }
                }

                VacationType.INCLUDE_WEEKEND -> {
                    buildAnnotatedString {
                        append(stringResource(NWKStringResource.HomePopularVacationIncludeWeekendSpan1))
                        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
                            append(stringResource(NWKStringResource.HomePopularVacationIncludeWeekendSpan2, 3))
                        }
                        append(stringResource(NWKStringResource.HomePopularVacationIncludeWeekendSpan3))
                    }
                }

                VacationType.BIRTHDAY_EXIST -> {
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
                            append(stringResource(NWKStringResource.HomePopularVacationBirthdayExistSpan1))
                        }
                        append(stringResource(NWKStringResource.HomePopularVacationBirthdayExistSpan2))
                    }
                }
            }
        }
    }
}

internal fun SandwichRecommendVacation.toPopularVacation() = PopularVacationUiModel(
    vacationType = VacationType.INCLUDE_MONDAY,
    startLocalDate = LocalDate.parse(this.startDate),
    endLocalDate = LocalDate.parse(this.endDate),
)
