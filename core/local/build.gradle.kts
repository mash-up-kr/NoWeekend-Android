plugins {
    alias(libs.plugins.team.noweekend.android.library)
    alias(libs.plugins.team.noweekend.android.room)
    alias(libs.plugins.team.noweekend.hilt)
}

android {
    namespace = "team.noweekend.core.local"
}

dependencies {
    implementation(project(":core:common-android"))
    implementation(project(":core:common-kotlin"))
}