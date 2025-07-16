package team.noweekend.feature.create.vacation

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import team.noweekend.core.navigator.model.CreateVacation
import team.noweekend.feature.create.vacation.date.navigation.vacationDateGraph
import team.noweekend.feature.create.vacation.information.navigation.informationGraph
import team.noweekend.feature.create.vacation.information.navigation.navigateToInformation
import team.noweekend.feature.create.vacation.recommend.navigation.recommendGraph

@Composable
internal fun CreateVacationNavHost(
    finish: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: CreateVacation = CreateVacation.Date,
) {
    val activity: ComponentActivity? = LocalActivity.current as? ComponentActivity
    val navController: NavHostController = rememberNavController()

    NavHost(
        modifier = modifier,
        startDestination = startDestination,
        navController = navController,
    ) {
        vacationDateGraph(
            navigateToHistoryBack = { navController.popBackStack(finish) },
            navigateToInformation = navController::navigateToInformation,
        )
        informationGraph(
            navigateToHistoryBack = { navController.popBackStack(finish) },
            navigateToHome = {
                activity?.run {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            },
        )
        recommendGraph(
            navigateToHistoryBack = { navController.popBackStack(finish) },
        )
    }
}

private fun NavHostController.popBackStack(action: () -> Unit) {
    this.popBackStack().also { if (!it) action() }
}
