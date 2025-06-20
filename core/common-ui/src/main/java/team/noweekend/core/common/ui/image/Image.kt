package team.noweekend.core.common.ui.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
fun NWKImage(
    @DrawableRes drawableResId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
) {
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(drawableResId)
                .build(),
        ),
        contentDescription = contentDescription,
        modifier = modifier,
        alignment = alignment,
        contentScale = contentScale,
        colorFilter = colorFilter,
    )
}

@Composable
fun NWKNetworkImage(
    model: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    placeholder: Painter? = null,
    error: Painter? = null,
    fallback: Painter? = error,
    onSuccess: ((Painter) -> Unit)? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DefaultFilterQuality,
    clipToBounds: Boolean = true,
) {
    AsyncImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier,
        placeholder = placeholder,
        error = error,
        fallback = fallback,
        onSuccess = onSuccess,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        filterQuality = filterQuality,
        clipToBounds = clipToBounds,
    )
}

@Composable
private fun AsyncImage(
    model: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    placeholder: Painter? = null,
    error: Painter? = null,
    fallback: Painter? = error,
    onSuccess: ((Painter) -> Unit)? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DefaultFilterQuality,
    clipToBounds: Boolean = true,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(model)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier,
        placeholder = placeholder,
        error = error,
        fallback = fallback,
        onLoading = null,
        onSuccess = if (onSuccess != null) {
            { onSuccess(it.painter) }
        } else {
            null
        },
        onError = null,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        filterQuality = filterQuality,
        clipToBounds = clipToBounds,
    )
}

@Preview
@Composable
private fun NWKImagePreview() {
    NWKTheme {
        Box(modifier = Modifier.background(NWKTheme.color.Neutral.white)) {
            NWKImage(
                drawableResId = NWKDrawableResource.MainToaster,
            )
        }
    }
}
