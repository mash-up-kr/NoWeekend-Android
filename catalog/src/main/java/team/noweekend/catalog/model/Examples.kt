package team.noweekend.catalog.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.Serializable
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.common.android.util.getScreenWidth
import team.noweekend.core.common.android.util.toDp
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.defaults.ButtonSizeType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.core.component.button.outline.NWKOutlineButton
import team.noweekend.core.design.system.core.component.card.NWKLongCard
import team.noweekend.core.design.system.core.component.control.page.NWKPageControl
import team.noweekend.core.design.system.core.component.divider.NWKHorizontalDivider
import team.noweekend.core.design.system.core.component.tabbar.NWKNavigationBarItem
import team.noweekend.core.design.system.core.component.tabbar.NavigationBarLayout
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource

@Serializable
data class Example(
    val name: String,
    val description: String,
    val content: @Composable () -> Unit,
)

internal object Button {
    private const val ButtonExampleDescription = "Button examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "FillButton",
                description = ButtonExampleDescription,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    NWKFillButton(
                        onClick = {},
                        text = "BTN",
                        modifier = Modifier.fillMaxWidth(),
                        type = BoxButtonType.PRIMARY,
                    )
                    NWKFillButton(
                        onClick = {},
                        text = "BTN",
                        modifier = Modifier.fillMaxWidth(),
                        type = BoxButtonType.BLACK,
                    )
                }
            },
            Example(
                name = "OutlineButton",
                description = ButtonExampleDescription,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    NWKOutlineButton(
                        onClick = {},
                        size = ButtonSizeType.EXTRA_LARGE,
                        text = "BTN",
                        modifier = Modifier.fillMaxWidth(),
                    )

                    NWKOutlineButton(
                        onClick = {},
                        size = ButtonSizeType.MEDIUM,
                        text = "BTN",
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
        )
}

internal object Input {
    private const val TextFieldExampleDescription = "Input examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Input",
                description = TextFieldExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object Calendar {
    private const val CalendarExampleDescription = "Calendar examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Calendar",
                description = CalendarExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object Card {
    private const val CardExampleDescription = "Card examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "LongCard",
                description = CardExampleDescription,
            ) {
                NWKLongCard(
                    title = "해외여행 떠나요",
                    description = "0/00(월) ~ 0/00(월)",
                    leadingDrawableResId = NWKDrawableResource.Cake,
                    trailingDrawableResId = NWKDrawableResource.Plus,
                    onCardClick = {},
                )
            },
        )
}

internal object Switch {
    private const val SwitchExampleDescription = "Switch examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Switch",
                description = SwitchExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object CheckBox {
    private const val CheckBoxExampleDescription = "CheckBox examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "CheckBox",
                description = CheckBoxExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object Divider {
    private const val DividerExampleDescription = "Divider examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Divider",
                description = DividerExampleDescription,
            ) {
                val (shouldFillScreenWidth, setFillScreenWidth) = remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(100.dp, Alignment.CenterVertically),
                ) {
                    NWKHorizontalDivider(
                        shouldFillScreenWidth = shouldFillScreenWidth,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    NWKOutlineButton(
                        onClick = { setFillScreenWidth(!shouldFillScreenWidth) },
                        size = ButtonSizeType.MEDIUM,
                        text = "변경",
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
        )
}

internal object PageControl {
    private const val PageControlExampleDescription = "Page Control examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "PageControl",
                description = PageControlExampleDescription,
            ) {
                val pagerState = rememberPagerState(pageCount = { 5 })
                val pageSize = (getScreenWidth() * 0.79f).toInt().toDp() // 350/250

                Column(
                    modifier = Modifier.fillMaxWidthOfScreen(),
                    verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space175),
                ) {
                    HorizontalPager(
                        state = pagerState,
                        pageSpacing = NWKTheme.spacing.space175,
                        contentPadding = PaddingValues(horizontal = NWKTheme.spacing.space200),
                        pageSize = PageSize.Fixed(pageSize = pageSize),
                    ) { index ->
                        NWKLongCard(
                            onCardClick = {},
                            title = "title",
                            description = "date",
                            leadingDrawableResId = NWKDrawableResource.Cake,
                            trailingDrawableResId = NWKDrawableResource.Plus,
                        )
                    }
                    NWKPageControl(
                        pageSize = pagerState.pageCount,
                        currentPosition = pagerState.currentPage,
                    )
                }
            },
        )
}

internal object Header {
    private const val HeaderExampleDescription = "Header examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Header",
                description = HeaderExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object TabBar {
    private const val TabBarExampleDescription = "TabBar examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "TabBar",
                description = TabBarExampleDescription,
            ) {
                val tabs: ImmutableList<ExampleTab> = ExampleTab.entries.toImmutableList()
                val currentTab: ExampleTab = ExampleTab.HOME
                Box(
                    modifier = Modifier
                        .fillMaxWidthOfScreen()
                        .fillMaxSize()
                        .background(NWKTheme.color.Neutral.neutralGray100),
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    NavigationBarLayout(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        tabs.forEach { tab ->
                            NWKNavigationBarItem(
                                onClick = {},
                                isSelected = tab == currentTab,
                                unselectedIconId = tab.unselectedIconResId,
                                selectedIconId = tab.selectedIconResId,
                                labelId = tab.labelId,
                                modifier = Modifier,
                            )
                        }
                    }
                }
            },
        )

    enum class ExampleTab(
        val labelId: Int,
        val selectedIconResId: Int,
        val unselectedIconResId: Int,
    ) {
        HOME(
            labelId = NWKStringResource.LabelHome,
            selectedIconResId = NWKDrawableResource.HomeOn,
            unselectedIconResId = NWKDrawableResource.HomeOff,
        ),
        CALENDAR(
            labelId = NWKStringResource.LabelCalendar,
            selectedIconResId = NWKDrawableResource.CalendarOn,
            unselectedIconResId = NWKDrawableResource.CalendarOff,
        ),
        PROFILE(
            labelId = NWKStringResource.LabelProfile,
            selectedIconResId = NWKDrawableResource.PersonOn,
            unselectedIconResId = NWKDrawableResource.PersonOff,
        ),
        ;
    }
}

internal object BottomSheet {
    private const val BottomSheetExampleDescription = "BottomSheet examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "BottomSheet",
                description = BottomSheetExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object Dialog {
    private const val DialogExampleDescription = "Dialog examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Dialog",
                description = DialogExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}
