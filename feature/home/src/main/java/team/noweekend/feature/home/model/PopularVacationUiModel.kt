package team.noweekend.feature.home.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.MONTH_DATE_WITH_DAY_OF_WEEK_KR_PATTERN
import team.noweekend.core.common.kotlin.extension.toFormattedString
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.model.vacation.VacationType
import team.noweekend.core.resource.NWKDrawableResource

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
            VacationType.BIRTHDAY_EXIST -> NWKDrawableResource.Sandwich
        }
    }

    private fun formatDisplayDate(startLocalDate: LocalDate, endLocalDate: LocalDate?): String {
        val startDisplayDate: String =
            startLocalDate.toFormattedString(LocalDate.MONTH_DATE_WITH_DAY_OF_WEEK_KR_PATTERN)
        val endDisplayDate: String? = endLocalDate?.toFormattedString(LocalDate.MONTH_DATE_WITH_DAY_OF_WEEK_KR_PATTERN)

        return if (endDisplayDate.isNullOrEmpty()) {
            "$startDisplayDate ~ $endDisplayDate"
        } else startDisplayDate
    }

    companion object {
        @Composable
        fun VacationType.getStyledDescription(): AnnotatedString {
            return when (this) {
                VacationType.HOLIDAY_EXIST -> {
                    buildAnnotatedString {
                        append("곧 ")
                        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
                            append("공휴일")
                        }
                        append("이\n")
                        append("다가와요")
                    }
                }

                VacationType.HOLIDAY_EXIST_NOT -> {
                    buildAnnotatedString {
                        append("이번 달엔\n공휴일이 없어요")
                    }
                }

                VacationType.INCLUDE_MONDAY -> {
                    buildAnnotatedString {
                        append("월요일에 연차쓰고\n")
                        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
                            append("3일 ")
                        }
                        append("쉬어요")
                    }
                }

                VacationType.INCLUDE_FRIDAY -> {
                    buildAnnotatedString {
                        append("금요일에 연차쓰고\n")
                        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
                            append("3일 ")
                        }
                        append("쉬어요")
                    }
                }

                VacationType.INCLUDE_WEEKEND -> {
                    buildAnnotatedString {
                        append("주말 포함 ")
                        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
                            append("4일")
                        }
                        append("쉴 수 있어요")
                    }
                }

                VacationType.BIRTHDAY_EXIST -> {
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
                            append("생일\n")
                        }
                        append("축하드려요")
                    }
                }
            }
        }
    }
}
