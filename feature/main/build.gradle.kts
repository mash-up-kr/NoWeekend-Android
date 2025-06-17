plugins {
    alias(libs.plugins.team.noweekend.android.feature)
}

android {
    namespace = "team.noweekend.feature.main"
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":feature:calendar"))
    implementation(project(":feature:profile"))
}
