package team.noweekend.feature.create.vacation.information.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.CreateVacation
import team.noweekend.feature.create.vacation.information.screen.InformationRoute

internal fun NavController.navigateToInformation() {
    navigate(CreateVacation.Information)
}

internal fun NavGraphBuilder.informationGraph(
    navigateToHistoryBack: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable<CreateVacation.Information>{
        InformationRoute(
            navigateToHistoryBack = navigateToHistoryBack,
            navigateToHome = navigateToHome,
        )
    }
}
