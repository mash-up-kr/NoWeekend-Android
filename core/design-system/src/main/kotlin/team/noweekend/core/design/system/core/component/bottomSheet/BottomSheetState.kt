package team.noweekend.core.design.system.core.component.bottomSheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberNWKBottomSheetState(
    bottomSheetType: BottomSheetType,
    sheetState: SheetState = rememberModalBottomSheetState(),
): NWKBottomSheetState {

    return remember(bottomSheetType, sheetState) {
        NWKBottomSheetState(
            bottomSheetType = bottomSheetType,
            sheetState = sheetState,
        )
    }
}

@Stable
@OptIn(ExperimentalMaterial3Api::class)
class NWKBottomSheetState(
    val sheetState: SheetState,
    val bottomSheetType: BottomSheetType,
)

