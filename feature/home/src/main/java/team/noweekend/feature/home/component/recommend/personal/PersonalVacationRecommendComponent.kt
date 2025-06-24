package team.noweekend.feature.home.component.recommend.personal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource
import team.noweekend.feature.home.component.recommend.personal.carousel.PersonalRecommendCarousel

internal fun LazyListScope.personalVacationRecommend(
    userName: String,
    vacationDays: Int,
    onCardClick: () -> Unit,
    onFilterClick: () -> Unit,
) = item {
    PersonalVacationRecommendComponent(
        userName = userName,
        vacationDays = vacationDays,
        onCardClick = onCardClick,
        onFilterClick = onFilterClick,
    )
}

@Composable
private fun PersonalVacationRecommendComponent(
    userName: String,
    vacationDays: Int,
    onCardClick: () -> Unit,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidthOfScreen()
            .background(NWKTheme.color.Neutral.neutralGray100)
            .padding(vertical = NWKTheme.spacing.space300),
    ) {
        PersonalVacationRecommendHeader(
            userName = userName,
            vacationDays = vacationDays,
            onFilterClick = onFilterClick,
        )
        PersonalRecommendCarousel(
            onCardClick = onCardClick,
        )
    }
}

@Composable
private fun PersonalVacationRecommendHeader(
    userName: String,
    vacationDays: Int,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val message = buildAnnotatedString {
        append(stringResource(NWKStringResource.HomePersonalRecommendHeaderSpan1, userName))
        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
            append(stringResource(NWKStringResource.HomePersonalRecommendHeaderSpan2, vacationDays))
        }
        append(stringResource(NWKStringResource.HomePersonalRecommendHeaderSpan3))
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = NWKTheme.spacing.space300,
                end = NWKTheme.spacing.space200,
                bottom = NWKTheme.spacing.space200,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = message,
            style = NWKTheme.typography.heading5.copy(
                color = NWKTheme.color.Semantic.Text.neutral,
            ),
        )
        NWKIcon(
            resourceId = NWKDrawableResource.Filter,
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    onClick = onFilterClick,
                ),
            tint = NWKTheme.color.Semantic.Text.body,
        )
    }
}

@Preview
@Composable
private fun PersonalVacationRecommendComponentPreview() {
    NWKTheme {
        NWKScaffold(
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                personalVacationRecommend(
                    userName = "재성",
                    vacationDays = 3,
                    onCardClick = {},
                    onFilterClick = {},
                )
            }
        }
    }
}

@Preview
@Composable
private fun PersonalVacationRecommendHeaderPreview() {
    NWKTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(NWKTheme.color.Neutral.white),
        ) {
            PersonalVacationRecommendHeader(
                userName = "재성",
                vacationDays = 3,
                onFilterClick = {},
            )
        }
    }
}
