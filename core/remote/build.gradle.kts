plugins {
    alias(libs.plugins.team.noweekend.android.library)
    alias(libs.plugins.team.noweekend.hilt)
}

android {
    namespace = "team.noweekend.core.remote"
}

dependencies {
    implementation(project(":core:common-android"))
    implementation(project(":core:common-kotlin"))
    implementation(project(":core:model"))

    implementation(libs.bundles.ktor)
    implementation(libs.bundles.kotlinx.coroutine)
}
