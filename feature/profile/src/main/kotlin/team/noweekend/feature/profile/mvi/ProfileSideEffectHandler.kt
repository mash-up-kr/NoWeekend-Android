package team.noweekend.feature.profile.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberProfileSideEffectHandler(
    navigateToExternalWebBrowser: (String) -> Unit,
): ProfileSideEffectHandler {
    return remember {
        ProfileSideEffectHandler(
            navigateToExternalWebBrowser = navigateToExternalWebBrowser,
        )
    }
}

@Stable
internal class ProfileSideEffectHandler(
    private val navigateToExternalWebBrowser: (String) -> Unit,
) : SideEffectHandler<SideEffect> {
    override fun handleSideEffect(sideEffect: SideEffect) {
        when (sideEffect) {
            is ProfileSideEffect.NavigateToExternalWebBrowser -> navigateToExternalWebBrowser(sideEffect.url)
            else -> {}
        }
    }
}
