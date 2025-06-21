package team.noweekend.feature.home.component.recommend.personal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

internal fun LazyListScope.personalVacationRecommend(
    userName: String,
    vacationDays: Int,
) = item {
    PersonalVacationRecommendComponent(
        userName = userName,
        vacationDays = vacationDays,
    )
}

@Composable
private fun PersonalVacationRecommendComponent(
    userName: String,
    vacationDays: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxWidthOfScreen()
            .background(NWKTheme.color.Neutral.neutralGray100),
    ) {
        PersonalVacationRecommendHeader(
            userName = userName,
            vacationDays = vacationDays,
        )
    }
}

@Composable
private fun PersonalVacationRecommendHeader(
    userName: String,
    vacationDays: Int,
    modifier: Modifier = Modifier,
) {
    val message = buildAnnotatedString {
        append("${userName}님을 위한 ")
        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
            append("${vacationDays}일 ")
        }
        append("휴가에요")
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = NWKTheme.spacing.space300,
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
        // TODO (JaesungLeee) : NWKIcon 대체 필요
        Icon(
            modifier = Modifier.size(24.dp),
            painter = rememberVectorPainter(ImageVector.vectorResource(NWKDrawableResource.Filter)),
            contentDescription = null,
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
            )
        }
    }
}
