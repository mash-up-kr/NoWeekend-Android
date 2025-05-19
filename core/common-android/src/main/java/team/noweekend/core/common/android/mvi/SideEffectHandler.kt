package team.noweekend.core.common.android.mvi

/**
 * SideEffectHandler에 대한 추상화 정의
 */
interface SideEffectHandler<SE : SideEffect> {
    fun handleSideEffect(sideEffect: SE)
}