package team.noweekend.core.design.system.foundation.typography

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import team.noweekend.core.design.system.foundation.color.token.NeutralColorToken
import team.noweekend.core.design.system.foundation.typography.token.FontToken

internal val LocalTypography = staticCompositionLocalOf { Typography() }

@Immutable
class Typography internal constructor(
    val heading2: TextStyle,
    val heading3: TextStyle,
    val heading4: TextStyle,
    val heading5: TextStyle,
    val heading6: TextStyle,
    val subTitle1: TextStyle,
    val subTitle2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,
    val body4: TextStyle,
) {
    constructor(
        color: Color = NeutralColorToken.Black,
        heading2: TextStyle = TextStyle(
            color = color,
            fontSize = 28.sp,
            fontWeight = FontWeight.W700,
            fontFamily = FontToken.Pretendard,
            fontStyle = FontStyle.Normal,
            lineHeight = 1.64.em,  // Line Height / Font Size
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            ),
            letterSpacing = -(0.004).em,
        ),
        heading3: TextStyle = TextStyle(
            color = color,
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            fontFamily = FontToken.Pretendard,
            fontStyle = FontStyle.Normal,
            lineHeight = 1.5.em,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            ),
            letterSpacing = -(0.003).em,
        ),
        heading4: TextStyle = TextStyle(
            color = color,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            fontFamily = FontToken.Pretendard,
            fontStyle = FontStyle.Normal,
            lineHeight = 1.5.em,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            ),
            letterSpacing = -(0.002).em,
        ),
        heading5: TextStyle = TextStyle(
            color = color,
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
            fontFamily = FontToken.Pretendard,
            fontStyle = FontStyle.Normal,
            lineHeight = 1.44.em,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            ),
            letterSpacing = -(0.001).em,
        ),
        heading6: TextStyle = TextStyle(
            color = color,
            fontSize = 16.sp,
            fontWeight = FontWeight.W700,
            fontFamily = FontToken.Pretendard,
            fontStyle = FontStyle.Normal,
            lineHeight = 1.5.em,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            ),
            letterSpacing = -(0.001).em,
        ),
        subTitle1: TextStyle = TextStyle(
            color = color,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            fontFamily = FontToken.Pretendard,
            fontStyle = FontStyle.Normal,
            lineHeight = 1.57.em,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            ),
        ),
        subTitle2: TextStyle = TextStyle(
            color = color,
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            fontFamily = FontToken.Pretendard,
            fontStyle = FontStyle.Normal,
            lineHeight = 1.5.em,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            ),
            letterSpacing = 0.em,
        ),
        body1: TextStyle = TextStyle(
            color = color,
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            fontFamily = FontToken.Pretendard,
            fontStyle = FontStyle.Normal,
            lineHeight = 1.5.em,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            ),
            letterSpacing = -(0.001).em,
        ),
        body2: TextStyle = TextStyle(
            color = color,
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            fontFamily = FontToken.Pretendard,
            fontStyle = FontStyle.Normal,
            lineHeight = 1.57.em,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            ),
            letterSpacing = -(0.001).em,
        ),
        body3: TextStyle = TextStyle(
            color = color,
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
            fontFamily = FontToken.Pretendard,
            fontStyle = FontStyle.Normal,
            lineHeight = 1.5.em,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            ),
            letterSpacing = -(0.001).em,
        ),
        body4: TextStyle = TextStyle(
            color = color,
            fontSize = 10.sp,
            fontWeight = FontWeight.W400,
            fontFamily = FontToken.Pretendard,
            fontStyle = FontStyle.Normal,
            lineHeight = 1.4.em,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None,
            ),
            letterSpacing = 0.em,
        ),
    ) : this(
        heading2 = heading2,
        heading3 = heading3,
        heading4 = heading4,
        heading5 = heading5,
        heading6 = heading6,
        subTitle1 = subTitle1,
        subTitle2 = subTitle2,
        body1 = body1,
        body2 = body2,
        body3 = body3,
        body4 = body4,
    )

    fun copy(
        heading2: TextStyle = this.heading2,
        heading3: TextStyle = this.heading3,
        heading4: TextStyle = this.heading4,
        heading5: TextStyle = this.heading5,
        heading6: TextStyle = this.heading6,
        subTitle1: TextStyle = this.subTitle1,
        subTitle2: TextStyle = this.subTitle2,
        body1: TextStyle = this.body1,
        body2: TextStyle = this.body2,
        body3: TextStyle = this.body3,
        body4: TextStyle = this.body4,
    ): Typography = Typography(
        heading2 = heading2,
        heading3 = heading3,
        heading4 = heading4,
        heading5 = heading5,
        heading6 = heading6,
        subTitle1 = subTitle1,
        subTitle2 = subTitle2,
        body1 = body1,
        body2 = body2,
        body3 = body3,
        body4 = body4,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Typography) return false

        if (heading2 != other.heading2) return false
        if (heading3 != other.heading3) return false
        if (heading4 != other.heading4) return false
        if (heading5 != other.heading5) return false
        if (heading6 != other.heading6) return false

        if (subTitle1 != other.subTitle1) return false
        if (subTitle2 != other.subTitle2) return false

        if (body1 != other.body1) return false
        if (body2 != other.body2) return false
        if (body3 != other.body3) return false
        if (body4 != other.body4) return false

        return true
    }

    override fun hashCode(): Int {
        var result = heading2.hashCode()
        result = 31 * result + heading3.hashCode()
        result = 31 * result + heading4.hashCode()
        result = 31 * result + heading5.hashCode()
        result = 31 * result + heading6.hashCode()

        result = 31 * result + subTitle1.hashCode()
        result = 31 * result + subTitle2.hashCode()

        result = 31 * result + body1.hashCode()
        result = 31 * result + body2.hashCode()
        result = 31 * result + body3.hashCode()
        result = 31 * result + body4.hashCode()

        return result
    }
}
