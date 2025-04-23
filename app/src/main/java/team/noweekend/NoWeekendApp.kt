package team.noweekend

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoWeekendApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
