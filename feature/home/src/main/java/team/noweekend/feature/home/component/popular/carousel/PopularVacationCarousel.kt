package team.noweekend.feature.home.component.popular.carousel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.design.system.core.component.card.NWKShortCard
import team.noweekend.core.design.system.core.component.control.page.NWKPageControl
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.model.vacation.VacationType
import team.noweekend.feature.home.component.common.carousel.CarouselLayout
import team.noweekend.feature.home.model.PopularVacationUiModel
import team.noweekend.feature.home.model.PopularVacationUiModel.Companion.getStyledDescription

@Composable
internal fun PopularVacationCarousel(
    popularVacations: ImmutableMap<Int, ImmutableList<PopularVacationUiModel>>,
    modifier: Modifier = Modifier,
) {
    val carouselState = rememberPagerState(pageCount = {
        popularVacations.keys.size
    },)

    Column(
        modifier = modifier.fillMaxWidthOfScreen(),
        verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space175),
    ) {
        CarouselLayout(
            state = carouselState,
            pageWidth = 335,
            pageSpacing = NWKTheme.spacing.space200,
            contentPadding = PaddingValues(horizontal = NWKTheme.spacing.space200),
            content = { index ->
                val popularVacations = popularVacations.getOrDefault(index, persistentListOf())
                PopularVacationCarouselGridLayout(
                    items = popularVacations,
                ) {
                    NWKShortCard(
                        date = popularVacations[it].displayDate,
                        drawableResId = popularVacations[it].imageResourceId,
                        description = popularVacations[it].vacationType.getStyledDescription(),
                        onCardClick = {},
                    )
                }
            },
        )
        NWKPageControl(
            pageSize = { carouselState.pageCount },
            currentPosition = { carouselState.currentPage },
        )
    }
}

@Composable
internal fun PopularVacationCarouselGridLayout(
    items: ImmutableList<PopularVacationUiModel>,
    modifier: Modifier = Modifier,
    columns: Int = 2,
    content: @Composable (Int) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space175),
    ) {
        val rows = (items.size + columns - 1) / columns

        for (rowIndex in 0 until rows) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space175),
            ) {
                for (columnIndex in 0 until columns) {
                    val itemIndex = rowIndex * columns + columnIndex
                    Box(
                        modifier = Modifier.weight(1f),
                    ) {
                        if (itemIndex < items.size) {
                            content(itemIndex)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PopularVacationCarouselPreview() {
    NWKTheme {
        PopularVacationCarousel(
            popularVacations = persistentMapOf(
                Pair(
                    0,
                    persistentListOf(
                        PopularVacationUiModel(
                            vacationType = VacationType.HOLIDAY_EXIST,
                            startLocalDate = LocalDate.now(),
                            endLocalDate = LocalDate.now(),
                        ),
                        PopularVacationUiModel(
                            vacationType = VacationType.BIRTHDAY_EXIST,
                            startLocalDate = LocalDate.now(),
                            endLocalDate = LocalDate.now(),
                        ),
                        PopularVacationUiModel(
                            vacationType = VacationType.INCLUDE_MONDAY,
                            startLocalDate = LocalDate.now(),
                            endLocalDate = LocalDate.now(),
                        ),
                        PopularVacationUiModel(
                            vacationType = VacationType.INCLUDE_MONDAY,
                            startLocalDate = LocalDate.now(),
                            endLocalDate = LocalDate.now(),
                        ),
                    ),
                ),
                Pair(
                    1,
                    persistentListOf(
                        PopularVacationUiModel(
                            vacationType = VacationType.HOLIDAY_EXIST,
                            startLocalDate = LocalDate.now(),
                            endLocalDate = LocalDate.now(),
                        ),
                        PopularVacationUiModel(
                            vacationType = VacationType.BIRTHDAY_EXIST,
                            startLocalDate = LocalDate.now(),
                            endLocalDate = LocalDate.now(),
                        ),
                        PopularVacationUiModel(
                            vacationType = VacationType.INCLUDE_MONDAY,
                            startLocalDate = LocalDate.now(),
                            endLocalDate = LocalDate.now(),
                        ),
                        PopularVacationUiModel(
                            vacationType = VacationType.INCLUDE_MONDAY,
                            startLocalDate = LocalDate.now(),
                            endLocalDate = LocalDate.now(),
                        ),
                    ),
                ),
                Pair(
                    2,
                    persistentListOf(
                        PopularVacationUiModel(
                            vacationType = VacationType.HOLIDAY_EXIST,
                            startLocalDate = LocalDate.now(),
                            endLocalDate = LocalDate.now(),
                        ),
                        PopularVacationUiModel(
                            vacationType = VacationType.BIRTHDAY_EXIST,
                            startLocalDate = LocalDate.now(),
                            endLocalDate = LocalDate.now(),
                        ),
                        PopularVacationUiModel(
                            vacationType = VacationType.INCLUDE_MONDAY,
                            startLocalDate = LocalDate.now(),
                            endLocalDate = LocalDate.now(),
                        ),
                        PopularVacationUiModel(
                            vacationType = VacationType.INCLUDE_MONDAY,
                            startLocalDate = LocalDate.now(),
                            endLocalDate = LocalDate.now(),
                        ),
                    ),
                ),
            ),
        )
    }
}
