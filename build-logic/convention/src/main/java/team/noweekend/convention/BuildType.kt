package team.noweekend.convention

import com.android.build.api.dsl.ApplicationExtension

internal fun ApplicationExtension.configureBuildType() {
    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
//            signingConfig = signingConfigs.getByName("dev")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }
}