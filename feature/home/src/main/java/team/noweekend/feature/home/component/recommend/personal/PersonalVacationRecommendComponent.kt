package team.noweekend.feature.home.component.recommend.personal

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

@Composable
internal fun PersonalVacationRecommendComponent(
    modifier: Modifier = Modifier
) {

}

val a: AnnotatedString = buildAnnotatedString {
    
}
@Composable
private fun PersonalVacationRecommendHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "유나님을 위한 3일 휴가에요",

        )
    }
}
