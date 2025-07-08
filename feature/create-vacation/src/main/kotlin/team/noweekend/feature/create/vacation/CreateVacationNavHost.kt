package team.noweekend.feature.create.vacation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import team.noweekend.core.navigator.model.CreateVacation
import team.noweekend.feature.create.vacation.date.navigation.vacationDateGraph
import team.noweekend.feature.create.vacation.information.navigation.informationGraph
import team.noweekend.feature.create.vacation.information.navigation.navigateToInformation
import team.noweekend.feature.create.vacation.recommend.navigation.navigateToVacationRecommendation
import team.noweekend.feature.create.vacation.recommend.navigation.recommendGraph

@Composable
internal fun CreateVacationNavHost(
    finish: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        modifier = modifier,
        startDestination = CreateVacation.Date,
        navController = navController,
    ) {
        vacationDateGraph(
            navigateToHistoryBack = { navController.popBackStack(finish) },
            navigateToInformation = navController::navigateToInformation,
        )
        informationGraph(
            navigateToHistoryBack = { navController.popBackStack(finish) },
            navigateToVacationRecommendation = navController::navigateToVacationRecommendation,
        )
        recommendGraph(
            navigateToHistoryBack = { navController.popBackStack(finish) },
        )
    }
}

private fun NavHostController.popBackStack(action: () -> Unit) {
    this.popBackStack().also { if (!it) action() }
}
