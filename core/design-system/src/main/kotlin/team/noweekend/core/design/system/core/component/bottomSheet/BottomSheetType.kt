package team.noweekend.core.design.system.core.component.bottomSheet

import androidx.compose.runtime.Immutable

/**
 * 바텀 시트 타입
 *
 * [UseButton] 하단 버튼을 사용 하는 경우
 *
 * [OnlyContents] 오직 컨텐츠만 사용 하는 경우
 */
@Immutable
sealed class BottomSheetType {

    /**
     * 하단 버튼 사용하는 경우
     */
    data class UseButton(
        /**
         * bottomSheet 상단 타이틀
         *
         * bottomSheetTitle은 비어있을수도 있습니다.
         */
        val bottomSheetTitle: String = "",
        /**
         * bottomSheet 하단 버튼 텍스트
         */
        val bottomSheetButtonTitle: String,
        /**
         * bottomSheet 하단 버튼 클릭 이벤트
         */
        val onClickButton: () -> Unit,
    ) : BottomSheetType()

    /**
     * 하단 버튼이 없는 경우
     */
    data object OnlyContents : BottomSheetType()
}
