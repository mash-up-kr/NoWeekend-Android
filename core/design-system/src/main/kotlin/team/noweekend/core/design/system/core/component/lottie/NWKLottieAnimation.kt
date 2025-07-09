package team.noweekend.core.design.system.core.component.lottie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun NWKLottieAnimation(
    lottieResId: Int,
    modifier: Modifier = Modifier,
    iteration: Int = LottieConstants.IterateForever,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(lottieResId),
    )
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(key1 = lottieComposition) {
        lottieAnimatable.animate(
            composition = lottieComposition,
            iteration = iteration,
        )
    }

    LottieAnimation(
        modifier = modifier,
        composition = lottieComposition,
        progress = {
            lottieAnimatable.progress
        },
        contentScale = contentScale,
    )
}
