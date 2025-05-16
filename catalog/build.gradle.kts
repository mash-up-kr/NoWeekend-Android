plugins {
    alias(libs.plugins.team.noweekend.android.application)
    alias(libs.plugins.team.noweekend.android.application.compose)
    alias(libs.plugins.team.noweekend.hilt)
}

android {
    namespace = "team.noweekend.catalog"

    defaultConfig {
        applicationId = "team.noweekend.app"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:design-system"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.navigation.compose)
    implementation(libs.bundles.kotlinx.coroutine)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

}