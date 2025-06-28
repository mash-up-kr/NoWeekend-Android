package team.noweekend.feature.create.vacation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import team.noweekend.core.navigator.model.CreateVacation
import team.noweekend.feature.create.vacation.date.navigation.vacationDateGraph
import team.noweekend.feature.create.vacation.recommend.navigation.recommendGraph
import team.noweekend.feature.create.vacation.information.navigation.informationGraph

@Composable
internal fun CreateVacationNavHost(
    modifier: Modifier = Modifier,
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        modifier = modifier,
        startDestination = CreateVacation.Date,
        navController = navController
    ) {
        vacationDateGraph()
        informationGraph()
        recommendGraph()
    }
}
