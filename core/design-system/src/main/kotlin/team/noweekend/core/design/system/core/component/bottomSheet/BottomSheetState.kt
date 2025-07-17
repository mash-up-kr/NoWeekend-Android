package team.noweekend.core.design.system.core.component.bottomSheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember


@Composable
fun rememberNWKBottomSheetState(
    bottomSheetType: BottomSheetType,
    isDraggable: Boolean = true,
    enableEdgeToEdge: Boolean = true,
    dismissOnClickOutside: Boolean = true,
): NWKBottomSheetState {

    return remember(bottomSheetType) {
        NWKBottomSheetState(
            bottomSheetType = bottomSheetType,
            isDraggable = isDraggable,
            enableEdgeToEdge = enableEdgeToEdge,
            dismissOnClickOutside = dismissOnClickOutside,
        )
    }
}

@Stable
@OptIn(ExperimentalMaterial3Api::class)
class NWKBottomSheetState(
    val bottomSheetType: BottomSheetType,
    val isDraggable: Boolean,
    val enableEdgeToEdge: Boolean,
    val dismissOnClickOutside: Boolean,
)
