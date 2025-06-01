package team.noweekend.catalog.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.noweekend.catalog.R
import team.noweekend.catalog.model.Component
import team.noweekend.core.design.system.foundation.theme.NoWeekendTheme

@Composable
internal fun ComponentItem(
    component: Component,
    onItemClick: (Component) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(180.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp),
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClick(component) })
            .padding(16.dp),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "",
        )
        Text(
            modifier = Modifier.align(Alignment.BottomStart),
            text = component.name,
            style = TextStyle(
                fontSize = 14.sp,
            ),
        )
    }
}

@Preview
@Composable
private fun ComponentItemPreview() {
    NoWeekendTheme {
        ComponentItem(
            component = Component.ButtonComponent,
            onItemClick = {},
        )
    }
}
