package team.noweekend.core.design.system.core.component.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun NWKScaffold(
    modifier: Modifier = Modifier,
    containerColor: Color = NWKTheme.color.Neutral.white,
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
                start = NWKTheme.spacing.space200,
                end = NWKTheme.spacing.space200,
                bottom = paddingValues.calculateBottomPadding(),
            )

            content(innerPadding)
        },
    )
}
