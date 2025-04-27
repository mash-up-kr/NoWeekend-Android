plugins {
    alias(libs.plugins.team.noweekend.android.application)
    alias(libs.plugins.team.noweekend.android.application.compose)
    alias(libs.plugins.team.noweekend.hilt)
}

android {
    namespace = "team.noweekend.app"

    defaultConfig {
        applicationId = "team.noweekend.app"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    }
}

dependencies {
    // Core modules
    implementation(project(":core:common-android"))
    implementation(project(":core:common-kotlin"))
    implementation(project(":core:common-ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:local"))
    implementation(project(":core:model"))
    implementation(project(":core:navigator"))
    implementation(project(":core:remote"))
    implementation(project(":core:resource"))

    // Feature modules
    implementation(project(":feature:home"))
    implementation(project(":feature:sample"))

    implementation(libs.hilt.navigation.compose)
}
