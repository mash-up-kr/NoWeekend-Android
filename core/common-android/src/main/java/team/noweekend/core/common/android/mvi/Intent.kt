package team.noweekend.core.common.android.mvi

sealed interface Intent {
    data object ClickBackButton : Intent
}