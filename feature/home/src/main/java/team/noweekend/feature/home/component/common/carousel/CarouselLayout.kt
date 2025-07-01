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

/**
 * Carousel 형태의 UI를 그리는 컴포넌트 입니다.
 * @param [state] Pager 상태를 관리하는 [PagerState]
 * @param [pageWidth] 한 페이지가 차지하는 너비, Figma 기준 너비를 작성합니다
 * @param [contentPadding] 전체 content의 padding
 * @param [content] Carousel 아이템
 *
 * @author JaesungLeee
 */
@Composable
fun CarouselLayout(
    state: PagerState,
    pageWidth: Int,
    pageSpacing: Dp,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    content: @Composable PagerScope.(index: Int) -> Unit,
) {
    val pageSize = (getScreenWidth() * (pageWidth.toFloat() / 375f)).toInt().toDp()

    HorizontalPager(
        modifier = modifier,
        state = state,
        pageSpacing = pageSpacing,
        contentPadding = contentPadding,
        pageSize = PageSize.Fixed(pageSize = pageSize),
        pageContent = { content(it) },
    )
}
