package team.noweekend.catalog

import kotlinx.serialization.Serializable

sealed interface CatalogRoute {
    @Serializable
    data object Home : CatalogRoute
}
