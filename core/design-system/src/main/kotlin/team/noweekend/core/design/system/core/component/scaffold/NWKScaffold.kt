package team.noweekend.core.design.system.core.component.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NWKScaffold(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,  // TODO(JaesungLeee) : color 수정
    includePadding: Boolean = true,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier.systemBarsPadding(),
        containerColor = containerColor,
        topBar = topBar,
        bottomBar = bottomBar,
        content = { paddingValues ->
            val innerPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                start = if (includePadding) 20.dp else 0.dp,
                end = if (includePadding) 20.dp else 0.dp,
                bottom = paddingValues.calculateBottomPadding(),
            )

            content(innerPadding)
        },
    )
}
