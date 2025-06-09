package team.noweekend.catalog.navigation

import kotlinx.serialization.Serializable

internal sealed interface CatalogRoute {
    @Serializable
    data object Home : CatalogRoute

    @Serializable
    data class Component(
        val componentId: Int,
    ) : CatalogRoute

    @Serializable
    data class Example(
        val componentId: Int,
        val exampleIndex: Int,
    ) : CatalogRoute
}
