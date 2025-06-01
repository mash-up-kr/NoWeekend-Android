package team.noweekend.catalog.component.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.noweekend.catalog.model.Button
import team.noweekend.catalog.model.Example
import team.noweekend.core.design.system.foundation.theme.NoWeekendTheme

@Composable
internal fun ExampleItem(
    example: Example,
    onExampleClick: (Example) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp),
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onExampleClick(example) })
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = example.name,
                style = TextStyle(
                    fontSize = 16.sp,
                ),
            )
            Text(
                text = example.description,
                style = TextStyle(
                    fontSize = 14.sp,
                ),
            )
        }
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "",
        )

    }
}

@Preview
@Composable
private fun ExampleItemPreview() {
    NoWeekendTheme {
        ExampleItem(
            example = Button.Examples.first(),
            onExampleClick = {},
        )
    }
}
