package team.noweekend.feature.addtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.core.common.android.extension.showToast
import team.noweekend.core.navigator.delegate.CalendarNavigationDelegate
import team.noweekend.core.navigator.feature.AddTaskNavigator
import team.noweekend.core.navigator.feature.MainNavigator
import javax.inject.Inject

@AndroidEntryPoint
class AddTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddTaskNavHost(
                navigateCalendar = { finish() },
                showSaveSuccessToast = { showToast("할 일을 추가했습니다.") },
                showErrorToast = { showToast("오류가 발생했습니다. 다시 시도해주세요.") },
            )
        }
    }
}
