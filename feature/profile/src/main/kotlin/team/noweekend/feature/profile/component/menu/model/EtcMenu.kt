package team.noweekend.feature.profile.component.menu.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.resource.NWKStringResource.EtcMenuContactServiceTitle
import team.noweekend.core.resource.NWKStringResource.EtcMenuCurrentVersionTitle
import team.noweekend.core.resource.NWKStringResource.EtcMenuPolicyTitle
import team.noweekend.core.resource.NWKStringResource.EtcMenuTitle

sealed interface EtcMenu : Menu {
    data class ContactService(
        override val title: Int = EtcMenuContactServiceTitle,
    ) : EtcMenu

    data class Policy(
        override val title: Int = EtcMenuPolicyTitle,
    ) : EtcMenu

    data class CurrentVersion(
        override val title: Int = EtcMenuCurrentVersionTitle,
        val versionCode: String,
    ) : EtcMenu

    companion object {
        val etcMenuList: ImmutableList<EtcMenu> = persistentListOf(
            ContactService(),
            Policy(),
            CurrentVersion(versionCode = "1.0.0"),
        )
    }
}
