package team.noweekend.core.navigator.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable
import javax.annotation.concurrent.Immutable

/**
 * 각 feature의 NavHost에서 전환 가능한 Composable의 Destination
 */
sealed interface DestinationRoute

@Serializable
data object Home : DestinationRoute

@Serializable
data object Calendar : DestinationRoute

@SuppressLint("UnsafeOptInUsageError")
@Immutable
@Serializable
data class DetailDate(
    val date: String,
    val todoList: String,
) : DestinationRoute

@Serializable
data object Profile : DestinationRoute

sealed interface CreateVacation : DestinationRoute {
    @Serializable
    data object Date : CreateVacation

    @SuppressLint("UnsafeOptInUsageError")
    @Serializable
    data class Information(
        val date: Int,
    ) : CreateVacation

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

sealed interface AddTask : DestinationRoute {
    @Serializable
    data object Main : AddTask

    @Serializable
    data object Detail : AddTask
}
