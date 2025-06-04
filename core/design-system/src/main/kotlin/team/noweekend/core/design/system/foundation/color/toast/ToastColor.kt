package team.noweekend.core.design.system.foundation.color.toast

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.color.token.ToastColorToken

internal val LocalToastColor: ProvidableCompositionLocal<ToastColor> =
    staticCompositionLocalOf {
        ToastColor(
            toast50 = ToastColorToken.Toast50,
            toast100 = ToastColorToken.Toast100,
            toast200 = ToastColorToken.Toast200,
            toast300 = ToastColorToken.Toast300,
            toast400 = ToastColorToken.Toast400,
            toast500 = ToastColorToken.Toast500,
            toast600 = ToastColorToken.Toast600,
            toast700 = ToastColorToken.Toast700,
            toast800 = ToastColorToken.Toast800,
            toast900 = ToastColorToken.Toast900,
            toast950 = ToastColorToken.Toast950,
        )
    }

data class ToastColor(
    val toast50: Color,
    val toast100: Color,
    val toast200: Color,
    val toast300: Color,
    val toast400: Color,
    val toast500: Color,
    val toast600: Color,
    val toast700: Color,
    val toast800: Color,
    val toast900: Color,
    val toast950: Color,
)
