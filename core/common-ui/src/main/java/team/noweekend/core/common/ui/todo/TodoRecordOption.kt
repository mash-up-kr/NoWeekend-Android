package team.noweekend.core.common.ui.todo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun TodoRecordOptionButton(
    modifier: Modifier = Modifier,
    onClickOptionButton: () -> Unit = {},
) {
    val backgroundColor = NWKTheme.color.Neutral.neutralGray700
    Row(
        modifier = modifier
            .size(32.dp)
            .padding(6.dp)
            .clickable {
                onClickOptionButton()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    )
    {
        Canvas(
            modifier = Modifier.size(3.dp),
        ) {
            drawCircle(color = backgroundColor)
        }
        Spacer(modifier = Modifier.width(2.dp))
        Canvas(
            modifier = Modifier.size(3.dp),
        ) {
            drawCircle(color = backgroundColor)
        }
        Spacer(modifier = Modifier.width(2.dp))
        Canvas(
            modifier = Modifier.size(3.dp),
        ) {
            drawCircle(color = backgroundColor)
        }
    }
}


@Preview
@Composable
private fun PreviewTodoRecordOptionButton() {
    NWKTheme {
        TodoRecordOptionButton()
    }
}
