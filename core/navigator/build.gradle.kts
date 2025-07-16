plugins {
    alias(libs.plugins.team.noweekend.android.library)
    alias(libs.plugins.team.noweekend.hilt)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "team.noweekend.core.navigator"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
