plugins {
    alias(libs.plugins.team.noweekend.android.library)
}

android {
    namespace = "team.noweekend.core.navigator"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // hilt navigation compose
    implementation(libs.hilt.navigation.compose)
    // navigation compose
    implementation(libs.navigation.compose)
}