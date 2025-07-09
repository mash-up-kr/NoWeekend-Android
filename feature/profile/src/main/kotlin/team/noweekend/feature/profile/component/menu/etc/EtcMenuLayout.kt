package team.noweekend.feature.profile.component.menu.etc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.EtcMenuTitle
import team.noweekend.feature.profile.component.menu.model.EtcMenu.Companion.etcMenuList
import team.noweekend.feature.profile.component.menu.model.InfoMenu.Companion.infoMenuList

@Composable
fun EtcMenuLayout(
    onClickMenuItem: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = EtcMenuTitle),
            style = NWKTheme.typography.body2,
            color = NWKTheme.color.Semantic.Text.body,
        )
        etcMenuList.forEachIndexed { index, etcMenu ->
            key(etcMenu) {
                EtcMenuComponent(
                    modifier = Modifier.fillMaxWidth(),
                    menu = etcMenu,
                    onClickMenuItem = onClickMenuItem,
                )
                if (index < infoMenuList.size - 1) {
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


@Preview(showBackground = true)
@Composable
private fun PreviewInfoMenuLayout() {
    NWKTheme {
        EtcMenuLayout(onClickMenuItem = {})
    }
}
