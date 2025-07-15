package team.noweekend.feature.profile.model

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.EtcMenuContactServiceTitle
import team.noweekend.core.resource.NWKStringResource.EtcMenuCurrentVersionTitle
import team.noweekend.core.resource.NWKStringResource.EtcMenuPolicyTitle
import team.noweekend.feature.profile.BuildConfig

@Stable
sealed interface EtcMenu : Menu {
    data class TermsOfService(
        override val title: Int = EtcMenuContactServiceTitle,
        override val url: String = "https://snow-chestnut-45b.notion.site/KR-EN-22ebee7f5996802ebe4bff5816112c35?source=copy_link",
    ) : EtcMenu, WebLinkMenu

    data class PrivacyPolicy(
        override val title: Int = EtcMenuPolicyTitle,
        override val url: String = "https://snow-chestnut-45b.notion.site/KR-EN-22ebee7f59968021846cdc54a1d0d987?source=copy_link",
    ) : EtcMenu, WebLinkMenu

    data class CurrentVersion(
        override val title: Int = EtcMenuCurrentVersionTitle,
        val versionCode: String,
        override val content: @Composable () -> Unit = {
            Text(
                modifier = Modifier.padding(end = 20.dp),
                text = "v. $versionCode",
                color = NWKTheme.color.Semantic.Text.body,
                style = NWKTheme.typography.body1.copy(
                    fontWeight = FontWeight.Medium,
                ),
            )
        },
    ) : EtcMenu

    companion object {
        val etcMenuList: ImmutableList<EtcMenu> = persistentListOf(
            TermsOfService(),
            PrivacyPolicy(),
            CurrentVersion(versionCode = BuildConfig.VERSION_NAME),
        )
    }
}
