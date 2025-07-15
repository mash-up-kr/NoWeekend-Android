package team.noweekend.core.common.ui.calendar.state

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.MutableStateFlow
import team.noweekend.core.common.ui.calendar.model.CalendarMode

@Composable
fun rememberCalendarPagerState() = remember {
    CalendarPagerState()
}

@Stable
class CalendarPagerState {

    var calendarMode: MutableStateFlow<CalendarMode> = MutableStateFlow(CalendarMode.WEEK)

    private val TAG = "CalendarState"

    val initialPage = Int.MAX_VALUE / 2 + 1
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
        updatePreviousWeekPage: (Int) -> Unit = { _ -> },
        updateNextWeekPage: (Int) -> Unit = { _ -> },
    ) {
        checkPageValid(currentPage)

        val localPreviousPage = previousWeekPage.lastOrNull() ?: currentPage

        previousWeekPage.add(currentPage)
        if (previousWeekPage.size > 2) previousWeekPage.removeAt(0)

        val direction = detectDirection(previousPage = localPreviousPage, currentPage)

        when (direction) {
            Direction.Previous -> {
                updatePreviousWeekPage(currentPage)
            }

            Direction.Next -> {
                updateNextWeekPage(currentPage)
            }

            Direction.Same -> {}
        }
    }

    suspend fun scrollToInitialWeekPage() {
        weekPagerState.scrollToPage(initialPage)
    }

    suspend fun scrollToMonthPage(page: Int = initialPage) {
        monthPagerState.scrollToPage(page)
    }

    fun updateMonthCalendar(
        currentPage: Int,
        updateNextMonthPage: (Int) -> Unit = { _ -> },
        updatePreviousMonthPage: (Int) -> Unit = { _ -> },
    ) {
        checkPageValid(currentPage)

        val localPreviousPage = previousMonthPage.lastOrNull() ?: currentPage

        previousMonthPage.add(currentPage)
        if (previousMonthPage.size > 2) previousMonthPage.removeAt(0)

        val direction = detectDirection(localPreviousPage, currentPage)
        when (direction) {
            Direction.Previous -> {
                updatePreviousMonthPage(currentPage)
            }

            Direction.Next -> {
                updateNextMonthPage(currentPage)
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
}
