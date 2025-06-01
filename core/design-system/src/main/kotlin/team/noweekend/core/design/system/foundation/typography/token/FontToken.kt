package team.noweekend.core.design.system.foundation.typography.token

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import team.noweekend.core.resource.R as RR

internal object FontToken {
    val Pretendard: FontFamily = FontFamily(
        Font(RR.font.pretendard_thin, FontWeight.W100),
        Font(RR.font.pretendard_extra_light, FontWeight.W200),
        Font(RR.font.pretendard_light, FontWeight.W300),
        Font(RR.font.pretendard_regular, FontWeight.W400),
        Font(RR.font.pretendard_medium, FontWeight.W500),
        Font(RR.font.pretendard_semi_bold, FontWeight.W600),
        Font(RR.font.pretendard_bold, FontWeight.W700),
        Font(RR.font.pretendard_extra_bold, FontWeight.W800),
        Font(RR.font.pretendard_black, FontWeight.W900),
    )
}
