package team.noweekend.core.navigator.model

import kotlinx.serialization.Serializable

/**
 * 각 feature의 NavHost에서 전환 가능한 Composable의 Destination
 */
sealed interface DestinationRoute

@Serializable
data object Home : DestinationRoute

@Serializable
data object Calendar : DestinationRoute

@Serializable
data object Profile : DestinationRoute

sealed interface CreateVacation : DestinationRoute {
    @Serializable
    data object Date : CreateVacation

    @Serializable
    data object Information : CreateVacation

    @Serializable
    data object Recommend : CreateVacation
}

sealed interface Onboard : DestinationRoute {
    @Serializable
    data object Profile : Onboard

    @Serializable
    data object RemainedVacation : Onboard

    @Serializable
    data object Schedule : Onboard
}
