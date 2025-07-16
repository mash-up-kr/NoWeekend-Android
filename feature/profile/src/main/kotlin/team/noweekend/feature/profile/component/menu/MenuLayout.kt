package team.noweekend.feature.profile.component.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.profile.model.Menu

@Composable
fun MenuLayout(
    menuList: ImmutableList<Menu>,
    onClickMenuItem: (Menu) -> Unit,
    modifier: Modifier = Modifier,
    title: String = "",
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        if (title.isNotEmpty()) {
            Text(
                text = title,
                style = NWKTheme.typography.body2,
                color = NWKTheme.color.Semantic.Text.body,
            )
        }
        menuList.forEachIndexed { index, menu ->
            key(menu) {
                MenuComponent(
                    modifier = Modifier.fillMaxWidth(),
                    menu = menu,
                    onClickMenuItem = { onClickMenuItem(menu) },
                )
                if (index < menuList.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = NWKTheme.color.Semantic.Border.border01,
                    )
                }
            }

        }
    }

}
