plugins {
    alias(libs.plugins.team.noweekend.android.library)
    alias(libs.plugins.team.noweekend.android.room)
    alias(libs.plugins.team.noweekend.hilt)
}

android {
    namespace = "team.noweekend.core.data"
}

dependencies {
    implementation(project(":core:common-android"))
    implementation(project(":core:common-kotlin"))
    implementation(project(":core:model"))
    implementation(project(":core:remote"))
    implementation(project(":core:local"))
    implementation(project(":core:domain"))
    
    // ktor
    implementation(libs.bundles.network)
    // coroutine
    implementation(libs.bundles.coroutine)
}