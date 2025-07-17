package team.noweekend.feature.home.component.common.bottomsheet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.design.system.core.component.bottomSheet.BottomSheetType
import team.noweekend.core.design.system.core.component.bottomSheet.NWKBottomSheet
import team.noweekend.core.design.system.core.component.bottomSheet.rememberNWKBottomSheetState
import team.noweekend.core.design.system.core.component.input.NWKInputField
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TaskTitleBottomSheet(
    onBottomSheetDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager: FocusManager = LocalFocusManager.current

    val titleTextFieldState: TextFieldState = rememberTextFieldState()
    val bottomSheetState = rememberNWKBottomSheetState(
        bottomSheetType = BottomSheetType.UseButton(
            bottomSheetTitle = "연차 제목을 작성하면\n할 일에 추가돼요",
            bottomSheetButtonTitle = "추가하기",
            onClickButton = {},
        ),
        isDraggable = false,
    )

    NWKBottomSheet(
        modifier = modifier,
        nwkBottomSheetState = bottomSheetState,
        shouldDismissOnBackPress = true,
        onDismissRequest = {
            focusManager.clearFocus()
            onBottomSheetDismiss()
        },
    ) {
        NWKInputField(
            textFieldState = titleTextFieldState,
            onKeyboardAction = { focusManager.clearFocus() },
            modifier = Modifier.fillMaxWidth(),
            placeholder = "쓸래말래가 추천한 연차 ✈",
            isSingLine = false,
        )
    }
}

@Preview
@Composable
private fun TaskTitleBottomSheetPreview() {
    NWKTheme {
        NWKScaffold {
            TaskTitleBottomSheet(
                onBottomSheetDismiss = {},
            )
        }
    }
}
