package team.noweekend.core.navigator.route

import kotlinx.serialization.Serializable

sealed interface NoWeekendRoute

sealed interface Sample : NoWeekendRoute {
    @Serializable
    data object Home : Sample
}
