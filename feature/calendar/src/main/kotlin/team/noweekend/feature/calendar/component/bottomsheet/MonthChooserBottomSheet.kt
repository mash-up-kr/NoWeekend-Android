package team.noweekend.feature.calendar.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.ui.datepicker.WheelDatePicker
import team.noweekend.core.common.ui.datepicker.model.DatePickerType
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.MonthChooseButtonTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthChooserBottomSheet(
    onClickSelectButton: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
) {

    val selectedDate = remember { mutableStateOf(LocalDate.now())}
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        modifier = modifier.fillMaxWidth(),
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = NWKTheme.color.Neutral.white,
    ) {
        Column(
            modifier= Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        ){
            WheelDatePicker(
                modifier= Modifier.align(Alignment.CenterHorizontally),
                onSelectedDate = { date->
                    selectedDate.value = date
                },
                wheelDatePickerType = DatePickerType.YearMonth,
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                NWKFillButton(
                    onClick = {
                        onClickSelectButton(selectedDate.value)
                    },
                    text = stringResource(id = MonthChooseButtonTitle),
                    type =  BoxButtonType.BLACK,
                    modifier = Modifier.fillMaxWidth()
                )
            }

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
                onDismissRequest = { isVisible.value = false },
            )

        }
    }
}
