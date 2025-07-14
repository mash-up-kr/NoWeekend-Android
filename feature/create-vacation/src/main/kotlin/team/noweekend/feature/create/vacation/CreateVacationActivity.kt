package team.noweekend.feature.create.vacation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.core.navigator.model.CreateVacation

@AndroidEntryPoint
class CreateVacationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val createVacationStatusTag by lazy { intent.getStringExtra("CREATE_VACATION_STATUS") }

        val startDestination: CreateVacation = when (createVacationStatusTag) {
            "Complete" -> CreateVacation.Recommend
            else -> CreateVacation.Date
        }

        setContent {
            CreateVacationNavHost(
                startDestination = startDestination,
                finish = this::finish,
            )
        }
    }
}
