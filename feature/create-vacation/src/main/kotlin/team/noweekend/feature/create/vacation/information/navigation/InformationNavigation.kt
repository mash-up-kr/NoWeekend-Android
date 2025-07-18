package team.noweekend.feature.create.vacation.information.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.CreateVacation
import team.noweekend.feature.create.vacation.information.screen.InformationRoute

internal fun NavController.navigateToInformation(date: String) {
    navigate(CreateVacation.Information(date.toInt()))
}

internal fun NavGraphBuilder.informationGraph(
    navigateToHistoryBack: () -> Unit,
    navigateToHome: (Int, String, String, String, String) -> Unit,
) {
    composable<CreateVacation.Information>{
        InformationRoute(
            navigateToHistoryBack = navigateToHistoryBack,
            navigateToHome = navigateToHome,
        )
    }
}
