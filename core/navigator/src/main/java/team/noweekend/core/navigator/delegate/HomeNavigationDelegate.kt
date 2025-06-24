package team.noweekend.core.navigator.delegate

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class HomeNavigationDelegate @Inject constructor(
    @ActivityContext private val context: Context,
) {}
