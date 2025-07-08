package team.noweekend.feature.create.vacation.recommend.component.result

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.create.vacation.recommend.model.RecommendVacationType
import team.noweekend.feature.create.vacation.recommend.model.RecommendVacationType.Companion.getRecommendedVacationImageResource
import team.noweekend.feature.create.vacation.recommend.model.RecommendedVacationUiModel
import kotlin.math.roundToInt

@Composable
internal fun ToastResultComponent(
    isLoading: Boolean,
    recommendedVacation: RecommendedVacationUiModel,
    modifier: Modifier = Modifier,
) {
    val targetOffsetY: Dp = if (isLoading) 84.dp else (-257).dp
    val animatedToastOffsetY: State<Dp> = animateDpAsState(targetValue = targetOffsetY)

    val (isRecommendDateTextVisible, setRecommendDateVisibility) = remember { mutableStateOf(false) }
    val animatedRecommendDateTextAlpha: State<Float> = animateFloatAsState(
        targetValue = if (isRecommendDateTextVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
    )

    LaunchedEffect(key1 = isLoading) {
        if (isLoading.not()) {
            delay(1000L)
            setRecommendDateVisibility(true)
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        RecommendDateText(
            recommendedDate = recommendedVacation.displayRecommendedDate,
            modifier = Modifier
                .offset(y = (-257).dp)
                .graphicsLayer {
                    alpha = animatedRecommendDateTextAlpha.value
                }
                .align(Alignment.CenterHorizontally),
        )
        Box(
            modifier = Modifier
                .size(260.dp)
                .offset {
                    val offsetY = with(this) { animatedToastOffsetY.value.toPx() }
                    IntOffset(
                        x = 0,
                        y = offsetY.roundToInt(),
                    )
                },
        ) {
            Image(
                modifier = Modifier,
                painter = painterResource(recommendedVacation.vacationType.getRecommendedVacationImageResource()),
                contentDescription = null,
            )
            Text(
                modifier = Modifier
                    .widthIn(max = 150.dp)
                    .offset(y = 20.dp)
                    .align(Alignment.Center),
                text = recommendedVacation.recommendedContent,
                style = NWKTheme.typography.heading3.copy(
                    fontWeight = FontWeight.W700,
                    color = NWKTheme.color.Toast.toast500,
                ),
            )
        }
    }
}

@Preview
@Composable
private fun ToastResultComponentPreview() {
    NWKTheme {
        Box {
            ToastResultComponent(
                isLoading = true,
                recommendedVacation = RecommendedVacationUiModel.INITIAL_DATA.copy(
                    recommendedContent = "나는 바보입니다",
                    vacationType = RecommendVacationType.LOCAL,
                ),
            )
        }
    }
}

