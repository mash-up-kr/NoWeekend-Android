package team.noweekend.core.common.android.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
@NonRestartableComposable
fun getScreenWidth(): Int {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    return with(LocalDensity.current) { screenWidth.toPx().toInt() }
}
