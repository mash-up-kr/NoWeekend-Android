package team.noweekend.feature.sample.home.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberSampleSideEffectHandler(
    navigateToMemberDetail: (List<String>) -> Unit,
): SampleSideEffectHandler {
    return remember {
        SampleSideEffectHandler(
            navigateToMemberDetail = navigateToMemberDetail,
        )
    }
}

internal class SampleSideEffectHandler(
    private val navigateToMemberDetail: (List<String>) -> Unit,
) : SideEffectHandler<SideEffect> {
    override fun handleSideEffect(sideEffect: SideEffect) {
        when (sideEffect) {
            is SampleSideEffect.NavigateToMemberDetail -> navigateToMemberDetail(sideEffect.members)
        }
    }
}
