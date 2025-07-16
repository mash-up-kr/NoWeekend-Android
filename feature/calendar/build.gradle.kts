plugins {
    alias(libs.plugins.team.noweekend.android.feature)
}

android {
    namespace = "team.noweekend.feature.calendar"
}
dependencies {
    implementation(libs.androidx.graphics.shapes.android)
}
