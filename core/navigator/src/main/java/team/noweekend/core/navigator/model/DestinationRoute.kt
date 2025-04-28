package team.noweekend.core.navigator.model

import kotlinx.serialization.Serializable

sealed interface DestinationRoute

sealed interface Sample : DestinationRoute {
    @Serializable
    data object Home : Sample
}
