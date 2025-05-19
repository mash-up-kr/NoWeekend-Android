package team.noweekend.core.common.android.mvi

/**
 * 기본 Intent 추상화 정의
 */
interface Intent {
    data object ClickBackButton : Intent
}