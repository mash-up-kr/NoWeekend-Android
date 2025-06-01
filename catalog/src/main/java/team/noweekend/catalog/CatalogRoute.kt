package team.noweekend.catalog

import kotlinx.serialization.Serializable

sealed interface CatalogRoute {
    @Serializable
    data object Home : CatalogRoute

    @Serializable
    data object Component : CatalogRoute

    @Serializable
    data object Example : CatalogRoute
}
