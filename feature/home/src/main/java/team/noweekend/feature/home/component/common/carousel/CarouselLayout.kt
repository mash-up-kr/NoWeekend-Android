package team.noweekend.feature.home.component.common.carousel

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import team.noweekend.core.common.android.util.getScreenWidth
import team.noweekend.core.common.android.util.toDp

@Composable
fun CarouselLayout(
    state: PagerState,
    pageWidth: Int,
    pageSpacing: Dp,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    content: @Composable PagerScope.(index: Int) -> Unit,
) {
    val pageSize = (getScreenWidth() * (pageWidth / 350f)).toInt().toDp()

    HorizontalPager(
        modifier = modifier,
        state = state,
        pageSpacing = pageSpacing,
        contentPadding = contentPadding,
        pageSize = PageSize.Fixed(pageSize = pageSize),
        pageContent = { content(it) },
    )
}
