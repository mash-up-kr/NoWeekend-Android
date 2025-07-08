package team.noweekend.feature.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.ProfileVacationBoardLessCountTitle
import team.noweekend.core.resource.NWKStringResource.ProfileVacationBoardUsedCountTitle
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

@Composable
internal fun VacationBoard(
    lessVacationCount: Float,
    usedVacationCount: Float,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(84.dp)
            .background(
                color = NWKTheme.color.Semantic.Background.alternative01,
                shape = NWKTheme.radius.borderRadius300,
            )
            .border(
                width = 1.dp, color = NWKTheme.color.Semantic.Border.border01,
                shape = NWKTheme.radius.borderRadius300,
            )
            .clip(shape = NWKTheme.radius.borderRadius300),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        VacationBoardContent(
            modifier = Modifier.weight(1f),
            vacationTitle = stringResource(id = ProfileVacationBoardLessCountTitle),
            vacationCount = lessVacationCount,
        )
        VerticalDivider(
            modifier = Modifier.height(24.dp),
            thickness = 1.dp,
            color = NWKTheme.color.Semantic.Border.border02,
        )
        VacationBoardContent(
            modifier = Modifier.weight(1f),
            vacationTitle = stringResource(id = ProfileVacationBoardUsedCountTitle),
            vacationCount = usedVacationCount,
        )

    }
}

@Composable
private fun VacationBoardContent(
    vacationTitle: String,
    vacationCount: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = vacationTitle,
            style = NWKTheme.typography.body2,
            color = NWKTheme.color.Semantic.Text.neutral,
        )
        Spacer(
            modifier = Modifier.height(4.dp),
        )
        val formatter = DecimalFormat("#.##", DecimalFormatSymbols(Locale.ROOT))
        val formattedText = formatter.format(vacationCount)

        Text(
            text = formattedText,
            style = NWKTheme.typography.heading5,
            color = NWKTheme.color.Semantic.Text.neutral,
        )
    }
}

@Preview
@Composable
private fun PreviewVacationBoard() {
    NWKTheme {
        VacationBoard(
            lessVacationCount = 12.5f,
            usedVacationCount = 3f,
        )
    }
}
