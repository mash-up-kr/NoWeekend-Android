package team.noweekend.core.common.ui.calendar.state

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import team.noweekend.core.resource.R

@Composable
fun rememberCalendarPagerState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember {
    CalendarPagerState(
        coroutineScope = coroutineScope,
    )
}

@Stable
class CalendarPagerState(
    private val coroutineScope: CoroutineScope,
) {
    enum class CalendarMode(@StringRes val id: Int) {
        WEEK(id = R.string.week), MONTH(id = R.string.month)
    }

    var calendarMode: MutableStateFlow<CalendarMode> = MutableStateFlow(CalendarMode.WEEK)

    var dataSize = 3

    private val TAG = "CalendarState"

    private val initialPage = (Int.MAX_VALUE / 2) - (Int.MAX_VALUE / 2 % dataSize) + 1
    private val maxPageCount = Int.MAX_VALUE

    val weekPagerState: PagerState = PagerState(
        currentPage = initialPage,
        pageCount = { maxPageCount },
    )

    private val previousWeekPage: SnapshotStateList<Int> = mutableStateListOf<Int>().apply {
        add(weekPagerState.currentPage)
    }

    val monthPagerState: PagerState = PagerState(
        currentPage = initialPage, // 인덱스 1부터 시작하도록 변경
        pageCount = { maxPageCount },
    )

    private val previousMonthPage: SnapshotStateList<Int> = mutableStateListOf<Int>().apply {
        add(monthPagerState.currentPage)
    }

    /**
     * [currentPage]는 page를 %3 으로 나누지 않은 값입니다.
     */
    fun updateWeekCalendar(
        currentPage: Int,
        updatePreviousWeekPage: (Int, Int) -> Unit = { _, _ -> },
        updateNextWeekPage: (Int, Int) -> Unit = { _, _ -> },
    ) {
        checkPageValid(currentPage)
        val localPreviousPage = previousWeekPage.lastOrNull() ?: currentPage
        val currentIndex = currentPage % dataSize

        val nextIndex = (currentPage + 1) % dataSize
        val prevIndex = (currentPage - 1) % dataSize

        previousWeekPage.add(currentPage)
        if (previousWeekPage.size > 2) previousWeekPage.removeAt(0)

        val direction = detectDirection(previousPage = localPreviousPage, currentPage)

        when (direction) {
            Direction.Previous -> {
                log("Moved to previous week. ")
                log("CurrentIndex: $currentIndex, Updated prevIndex $prevIndex")
                updatePreviousWeekPage(currentIndex, prevIndex)
            }

            Direction.Next -> {
                log("Moved to next week. ")
                log("CurrentIndex: $currentIndex, Updated nextIndex $nextIndex")
                updateNextWeekPage(currentIndex, nextIndex)
            }

            Direction.Same -> {}
        }
    }

    fun scrollToInitialWeekPage() {
        coroutineScope.launch {
            weekPagerState.scrollToPage(initialPage)
        }
    }

    fun scrollToMonthPage(page: Int = initialPage) {
        coroutineScope.launch {
            monthPagerState.scrollToPage(page)
        }
    }

    fun updateMonthCalendar(
        currentPage: Int,
        updateNextMonthPage: (Int, Int) -> Unit = { _, _ -> },
        updatePreviousMonthPage: (Int, Int) -> Unit = { _, _ -> },
    ) {
        checkPageValid(currentPage)
        val currentIndex = currentPage % dataSize

        val localPreviousPage = previousMonthPage.lastOrNull() ?: currentIndex

        val nextIndex = (currentPage + 1) % dataSize
        val prevIndex = (currentPage - 1) % dataSize

        previousMonthPage.add(currentIndex)
        if (previousMonthPage.size > 2) previousMonthPage.removeAt(0)

        val direction = detectDirection(localPreviousPage, currentIndex)
        when (direction) {
            Direction.Previous -> {
                log("Moved to previous month")
                log("CurrentIndex: $currentIndex, Updated prevIndex $prevIndex")
                updatePreviousMonthPage(currentIndex, prevIndex)
            }

            Direction.Next -> {
                log("Moved to next month.")
                log("CurrentIndex: $currentIndex, Updated nextIndex $nextIndex")
                updateNextMonthPage(currentIndex, nextIndex)
            }

            Direction.Same -> {
                return
            }
        }
    }

    private fun checkPageValid(currentPage: Int) {
        require(currentPage >= 3) {
            error("currentPage는 PagerState의 currentPage를 사용해야합니다.")
        }
    }

    fun updateCalendarMode(calendarMode: CalendarMode) {
        this.calendarMode.value = calendarMode
    }

    enum class ImageType(@DrawableRes val id: Int) {
        NONE(id = R.drawable.ic_day_type_none),
        FutureSchedule(id = R.drawable.ic_day_type_future_schedule),
        BurnOut(id = R.drawable.ic_day_type_burnout),
        Rest(id = R.drawable.ic_day_type_rest),
        OverZeroUnderFiftyDegree(id = R.drawable.ic_day_type_over_zero_under_fifty_degree),
        OverFiftyUnderSeventyFive(id = R.drawable.ic_day_type_over_fifty_under_seventy_degree),
    }

    enum class Direction {
        Previous, Next, Same
    }

    private fun detectDirection(previousPage: Int, currentPage: Int): Direction {
        return when {
            previousPage == 0 && currentPage == 2 -> Direction.Previous
            previousPage == 2 && currentPage == 0 -> Direction.Next
            currentPage > previousPage -> Direction.Next
            currentPage < previousPage -> Direction.Previous
            else -> Direction.Same
        }
    }

    private fun log(message: String, isDebugLevel: Boolean = true) {
        if (isDebugLevel) {
            Log.d(TAG, message)
        } else {
            Log.e(TAG, message)
        }
    }
}
