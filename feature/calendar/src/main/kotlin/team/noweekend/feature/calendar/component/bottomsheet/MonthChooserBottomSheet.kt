package team.noweekend.feature.calendar.component.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.ui.datepicker.WheelDatePicker
import team.noweekend.core.common.ui.datepicker.model.DatePickerType
import team.noweekend.core.design.system.core.component.bottomSheet.BottomSheetType
import team.noweekend.core.design.system.core.component.bottomSheet.NWKBottomSheet
import team.noweekend.core.design.system.core.component.bottomSheet.rememberNWKBottomSheetState
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.MonthChooseButtonTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthChooserBottomSheet(
    onClickSelectButton: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    bottomSheetTitle: String = "",
    onDismissRequest: () -> Unit = {},
) {

    val selectedDate = remember { mutableStateOf(LocalDate.now()) }

    val nwkBottomSheetState = rememberNWKBottomSheetState(
        bottomSheetType = BottomSheetType.UseButton(
            bottomSheetButtonTitle = stringResource(id = MonthChooseButtonTitle),
            onClickButton = {
                onClickSelectButton(selectedDate.value)
            },
            bottomSheetTitle = bottomSheetTitle,
        ),
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        ),
    )

    NWKBottomSheet(
        modifier = modifier.fillMaxWidth(),
        nwkBottomSheetState = nwkBottomSheetState,
        onDismissRequest = onDismissRequest,
        containerColor = NWKTheme.color.Neutral.white,
        shouldDismissOnBackPress = true,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            WheelDatePicker(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onSelectedDate = { date ->
                    selectedDate.value = date
                },
                wheelDatePickerType = DatePickerType.YearMonth,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMonthChooserBottomSheet() {
    NWKTheme {
        val isVisible = remember { mutableStateOf(true) }
        if (isVisible.value) {
            MonthChooserBottomSheet(
                onClickSelectButton = {
                    println(it)
                },
                bottomSheetTitle = "확인하고 싶은\n" +
                    "휴가 날짜를 선택하세요",
                onDismissRequest = { isVisible.value = false },
            )
        }
    }
}
