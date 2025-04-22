plugins {
    alias(libs.plugins.team.noweekend.android.library)
}

android {
    namespace = "team.noweekend.core.navigator"
}

dependencies {
    // hilt navigation compose
    implementation(libs.hilt.navigation.compose)
    // navigation compose
    implementation(libs.navigation.compose)
}