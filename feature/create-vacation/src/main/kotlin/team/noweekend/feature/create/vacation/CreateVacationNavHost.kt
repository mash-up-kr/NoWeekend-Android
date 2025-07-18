package team.noweekend.feature.create.vacation

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import team.noweekend.core.navigator.extra.CREATE_VACATION_ACTIVITY_TYPE
import team.noweekend.core.navigator.extra.CREATE_VACATION_BUNDLE
import team.noweekend.core.navigator.extra.CREATE_VACATION_DAYS
import team.noweekend.core.navigator.extra.CREATE_VACATION_LEISURE_PREFERENCE
import team.noweekend.core.navigator.extra.CREATE_VACATION_REST_PREFERENCE
import team.noweekend.core.navigator.extra.CREATE_VACATION_TRAVEL_STYLE
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
            navigateToHome = { days: Int, travelStyle: String, activityType: String, restPreference: String, leisurePreference: String ->

                val intent = Intent().apply {
                    putExtra(CREATE_VACATION_DAYS, days)
                    putExtra(CREATE_VACATION_TRAVEL_STYLE, travelStyle)
                    putExtra(CREATE_VACATION_ACTIVITY_TYPE, activityType)
                    putExtra(CREATE_VACATION_REST_PREFERENCE, restPreference)
                    putExtra(CREATE_VACATION_LEISURE_PREFERENCE, leisurePreference)
                }

                activity?.run {
                    setResult(Activity.RESULT_OK, intent)
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
