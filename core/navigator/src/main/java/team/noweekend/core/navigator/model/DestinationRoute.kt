package team.noweekend.core.navigator.model

import kotlinx.serialization.Serializable

/**
 * 각 feature의 NavHost에서 전환 가능한 Composable의 Destination
 */
sealed interface DestinationRoute

sealed interface Sample : DestinationRoute {
    @Serializable
    data object Home : Sample

    @Serializable
    data class Detail(
        val members: List<String>
    ) : Sample
}
