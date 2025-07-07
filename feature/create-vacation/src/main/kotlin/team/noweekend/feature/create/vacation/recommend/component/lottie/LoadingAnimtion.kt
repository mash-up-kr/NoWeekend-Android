package team.noweekend.feature.create.vacation.recommend.component.lottie

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
import team.noweekend.core.resource.NWKDrawableResource

@Composable
internal fun LoadingAnimation(
    modifier: Modifier = Modifier,
) {
    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(NWKDrawableResource.Loading),
    )
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(key1 = lottieComposition) {
        lottieAnimatable.animate(
            composition = lottieComposition,
            iteration = LottieConstants.IterateForever
        )
    }

    LottieAnimation(
        modifier = modifier,
        composition = lottieComposition,
        progress = {
            lottieAnimatable.progress
        },
        contentScale = ContentScale.Crop,
    )
}
