package team.noweekend.feature.create.vacation.date.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.CreateVacation
import team.noweekend.feature.create.vacation.date.screen.VacationDateRoute

internal fun NavGraphBuilder.vacationDateGraph() {
    composable<CreateVacation.Date> {
        VacationDateRoute()
    }
}
