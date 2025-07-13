package team.noweekend.feature.home.component.common.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

internal fun LazyListScope.itemSpacer(
    space: Dp,
    modifier: Modifier = Modifier,
) {
    item {
        Spacer(modifier = modifier.size(space))
    }
}
