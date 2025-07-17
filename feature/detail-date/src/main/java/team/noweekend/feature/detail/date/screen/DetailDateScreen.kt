package team.noweekend.feature.detail.date.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.now
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
        Spacer(modifier = Modifier.height(16.dp))
        DegreeGauge(
            degree = detailDateUiState.degreeUiModel.degree,
            isAnnualLeave = detailDateUiState.degreeUiModel.isAnnualLeave,
        )
        Spacer(modifier = Modifier.height(16.dp))
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
                date = LocalDate.now(),
                todoList = persistentListOf(),
                recommendTodoTagList = persistentListOf(),
            ),
            onClickBackButton = {},
            onClickOptionButton = {},
            onClickCheckBox = {},
        )
    }
}
