package team.noweekend.catalog.navigation

import kotlinx.serialization.Serializable
import team.noweekend.catalog.model.Component as NDSComponent

internal sealed interface CatalogRoute {
    @Serializable
    data object Home : CatalogRoute

    @Serializable
    data class Component(
        val component: NDSComponent,
    ) : CatalogRoute

    @Serializable
    data object Example : CatalogRoute
}
