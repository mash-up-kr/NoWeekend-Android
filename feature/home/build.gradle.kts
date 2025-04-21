plugins {
    alias(libs.plugins.team.noweekend.android.feature)
}

android {
    namespace = "team.noweekend.feature.home"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}