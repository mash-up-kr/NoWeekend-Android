package team.noweekend.feature.detail.date.mvi.builder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import team.noweekend.feature.detail.date.mvi.DetailDateIntent

@Composable
fun rememberIntentBuilder(
    send: (DetailDateIntent) -> Unit,
) = remember {
    IntentBuilder(send = send)
}

@Stable
class IntentBuilder(
    private val send: (DetailDateIntent) -> Unit,
) {

    private fun build(detailDateIntent: DetailDateIntent) {
        send(detailDateIntent)
    }

    fun changeCompleteSchedule(index: Int) {
        build(DetailDateIntent.ChangeComplete(index = index))
    }

}
