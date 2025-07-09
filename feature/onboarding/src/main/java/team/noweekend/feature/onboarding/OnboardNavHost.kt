package team.noweekend.feature.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import team.noweekend.core.navigator.model.Onboard
import team.noweekend.feature.onboarding.profile.navigation.profileGraph
import team.noweekend.feature.onboarding.schedule.navigation.scheduleGraph
import team.noweekend.feature.onboarding.vacation.navigation.remainedVacationGraph

@Composable
internal fun OnboardNavHost(
    modifier: Modifier = Modifier,
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Onboard.Profile,
    ) {
        profileGraph()
        remainedVacationGraph()
        scheduleGraph()
    }
}
