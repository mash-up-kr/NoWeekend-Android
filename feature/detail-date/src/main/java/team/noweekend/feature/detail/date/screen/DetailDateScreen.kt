package team.noweekend.feature.detail.date.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.common.ui.calendar.component.CalendarTodoList
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.detail.date.component.DegreeCard
import team.noweekend.feature.detail.date.component.DetailDateHeader
import team.noweekend.feature.detail.date.component.degreeGauge.DegreeGauge
import team.noweekend.feature.detail.date.model.DegreeUIModel
import team.noweekend.feature.detail.date.mvi.DetailDateUiState

@Composable
fun DetailDateScreen(
    detailDateUiState: DetailDateUiState,
    onClickBackButton: () -> Unit,
    onClickCheckBox: (Int) -> Unit,
    onClickOptionButton: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        DetailDateHeader(
            dateTitle = detailDateUiState.dateTitle,
            onClickBackButton = onClickBackButton,
        )
        DegreeCard(
            degreeUIModel = detailDateUiState.degreeUiModel,
        )
        DegreeGauge(
            degree = detailDateUiState.degreeUiModel.degree,
            isAnnualLeave = detailDateUiState.degreeUiModel.isAnnualLeave,
        )

        if (detailDateUiState.todoList.isNotEmpty()) {
            CalendarTodoList(
                todoList = detailDateUiState.todoList,
                onClickCheckBox = onClickCheckBox,
                onClickOptionButton = onClickOptionButton,
            )
        }
    }

}


@Preview
@Composable
private fun PreviewDateDetailScreen() {
    NWKTheme {
        DetailDateScreen(
            modifier = Modifier.fillMaxSize(),
            detailDateUiState = DetailDateUiState(
                dateTitle = "2023년 10월 10일",
                degreeUiModel = DegreeUIModel(
                    degree = 50,
                    isAnnualLeave = true,
                ),
                todoList = persistentListOf()
            ),
            onClickBackButton = {},
            onClickOptionButton = {},
            onClickCheckBox = {},
        )
    }
}
