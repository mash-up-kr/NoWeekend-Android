package team.noweekend.core.common.ui.calendar.model

import androidx.compose.runtime.Immutable
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.ImageType
import java.time.LocalDate

@Immutable
data class DateOfWeek(
    val imageType: ImageType,
    val localDate: LocalDate,
)
