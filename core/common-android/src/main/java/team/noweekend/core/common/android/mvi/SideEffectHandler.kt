package team.noweekend.core.common.android.mvi

interface SideEffectHandler<SE : SideEffect> {
    fun handleSideEffect(sideEffect: SE)
}