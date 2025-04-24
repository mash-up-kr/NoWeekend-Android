plugins {
    alias(libs.plugins.team.noweekend.android.library)
    alias(libs.plugins.team.noweekend.hilt)
}

android {
    namespace = "team.noweekend.core.data"
}

dependencies {
    implementation(project(":core:common-kotlin"))
    implementation(project(":core:model"))
    implementation(project(":core:remote"))
    implementation(project(":core:domain"))

    // ktor
    implementation(libs.bundles.network)
    // coroutine
    implementation(libs.bundles.coroutine)
}
