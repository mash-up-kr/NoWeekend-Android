package team.noweekend.feature.profile.component.topbar.core

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen

@Composable
fun Header(
    vararg components: @Composable (Modifier) -> Unit,
    alignment: HeaderAlignmentType,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidthOfScreen()
            .height(56.dp)
            .padding(start = 12.dp, end = if(components.size == 2 && alignment == HeaderAlignmentType.CenterFill) 40.dp else 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        components.forEachIndexed { index, component ->
            key(index) {
                when (alignment) {
                    HeaderAlignmentType.StartFill -> {
                        if (index == 0) {
                            component(Modifier.padding(start= 12.dp).weight(1f))
                        } else {
                            component(Modifier)
                        }
                    }

                    HeaderAlignmentType.CenterFill -> {
                        if (index == 1) {
                            component(Modifier.weight(1f))
                        } else {
                            component(Modifier)
                        }
                    }
                }

            }

        }
    }
}


enum class HeaderAlignmentType {
    StartFill, CenterFill,
    ;
}
