package team.noweekend.core.design.system.core.component.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NWKBottomSheet(
    onDismissRequest: () -> Unit,
    shouldDismissOnBackPress: Boolean,
    nwkBottomSheetState: NWKBottomSheetState,
    modifier: Modifier = Modifier,
    containerColor: Color = BottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    content: @Composable () -> Unit,
) {
    ModalBottomSheet(
        modifier = modifier.fillMaxWidth(),
        sheetState = nwkBottomSheetState.sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = containerColor,
        contentColor = contentColor,
        properties = ModalBottomSheetProperties(
            shouldDismissOnBackPress = shouldDismissOnBackPress,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val sheetType = nwkBottomSheetState.bottomSheetType
            if (sheetType is BottomSheetType.UseButton &&
                sheetType.bottomSheetTitle.isNotEmpty()
            ) {
                Text(
                    text = sheetType.bottomSheetTitle,
                    style = NWKTheme.typography.heading4,
                    color = NWKTheme.color.Neutral.black,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            content()

            if (sheetType is BottomSheetType.UseButton) {
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    NWKFillButton(
                        modifier = Modifier.fillMaxWidth(),
                        type = BoxButtonType.BLACK,
                        onClick = sheetType.onClickButton,
                    ) {
                        Text(
                            text = sheetType.bottomSheetButtonTitle,
                            color = NWKTheme.color.Neutral.white,
                            style = NWKTheme.typography.heading6,
                        )
                    }
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PreviewNWKBottomSheet() {

    val bottomSheetState = rememberNWKBottomSheetState(
        bottomSheetType = BottomSheetType.UseButton(
            bottomSheetTitle = "타이틀",
            bottomSheetButtonTitle = "확인",
            onClickButton = {
                println("onClick")
            },
        ),
    )
    NWKTheme {
        NWKBottomSheet(
            onDismissRequest = { /* Handle dismiss request */ },
            shouldDismissOnBackPress = false,
            nwkBottomSheetState = bottomSheetState,
        ) {
            Text(
                text = "hi",
            )
        }
    }
}
