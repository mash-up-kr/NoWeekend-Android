package team.noweekend.feature.calendar.component.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.image.NWKImage
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.calendar.model.TodoRecordAction

@Composable
internal fun TodoRecordActionComponent(
    todoRecordAction: TodoRecordAction,
    modifier: Modifier = Modifier,
    onClickAction : (TodoRecordAction) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp).clickable{
                onClickAction(todoRecordAction)
            }
            .padding(
                start = 20.dp,
                top = 16.dp,
                bottom = 16.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        NWKImage(
            modifier = Modifier.size(24.dp),
            drawableResId = todoRecordAction.imageResId,
        )
        Spacer(
            modifier = Modifier.width(8.dp),
        )
        Text(
            text = stringResource(id = todoRecordAction.title),
            style = NWKTheme.typography.body1.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = NWKTheme.color.Semantic.Text.neutral,
        )
    }
}
