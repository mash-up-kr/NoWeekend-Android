package team.noweekend.core.common.android.mvi

/**
 * 기본 SideEffect 추상화 정의
 */
interface SideEffect {
    data object NavigateToHistoryBack : SideEffect
}