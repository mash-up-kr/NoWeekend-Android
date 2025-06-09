package team.noweekend.catalog.example.mvi

import team.noweekend.core.common.android.mvi.Intent

interface ExampleIntent : Intent {
    data object ClickBackButton : ExampleIntent
}
