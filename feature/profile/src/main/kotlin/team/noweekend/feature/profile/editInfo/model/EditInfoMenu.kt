package team.noweekend.feature.profile.editInfo.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.design.system.core.component.image.NWKImage
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource.ChevronRight
import team.noweekend.core.resource.NWKDrawableResource.GoogleLogo
import team.noweekend.core.resource.NWKStringResource.EditInfoGoogleAccountTitle
import team.noweekend.core.resource.NWKStringResource.EditInfoMenuAccountTitle
import team.noweekend.core.resource.NWKStringResource.EditInfoMenuFrequentScheduleTitle
import team.noweekend.core.resource.NWKStringResource.EditInfoMenuLogoutTitle
import team.noweekend.feature.profile.model.Menu

@Stable
sealed interface EditInfoMenu : Menu {
    data class Account(
        override val title: Int = EditInfoMenuAccountTitle,
        val accountName: String,
        override val content: @Composable () -> Unit = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = accountName,
                    style = NWKTheme.typography.body1,
                    color = NWKTheme.color.Semantic.Text.neutral,
                )
                NWKImage(
                    modifier = Modifier.size(24.dp),
                    drawableResId = ChevronRight,
                )
            }
        },
    ) : EditInfoMenu

    data class FrequentSchedule(
        override val title: Int = EditInfoMenuFrequentScheduleTitle,
        override val content: @Composable () -> Unit = {
            NWKImage(
                modifier = Modifier.size(24.dp),
                drawableResId = ChevronRight,
            )
        },
    ) : EditInfoMenu

    data class Logout(
        override val title: Int = EditInfoMenuLogoutTitle,
        override val content: @Composable () -> Unit = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                NWKImage(
                    modifier= Modifier.size(24.dp),
                    drawableResId = GoogleLogo,
                )
                Text(
                    text = stringResource(id = EditInfoGoogleAccountTitle),
                    style = NWKTheme.typography.body1,
                    color = NWKTheme.color.Semantic.Text.neutral,
                )
                NWKImage(
                    modifier = Modifier.size(24.dp),
                    drawableResId = ChevronRight,
                )
            }
        },
    ) : EditInfoMenu

    companion object {
        val editInfoMenuList: ImmutableList<EditInfoMenu> = persistentListOf(
            Account(accountName = "김매숑"), FrequentSchedule(), Logout(),
        )
    }
}
