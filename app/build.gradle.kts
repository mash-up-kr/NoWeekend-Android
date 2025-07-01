plugins {
    alias(libs.plugins.team.noweekend.android.application.compose)
    alias(libs.plugins.team.noweekend.hilt)
}

android {
    namespace = "team.noweekend.app"
}

dependencies {
    // Core modules
    implementation(project(":core:common-android"))
    implementation(project(":core:common-kotlin"))
    implementation(project(":core:common-ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:design-system"))
    implementation(project(":core:local"))
    implementation(project(":core:model"))
    implementation(project(":core:navigator"))
    implementation(project(":core:remote"))
    implementation(project(":core:resource"))

    // Feature modules
    implementation(project(":feature:main"))
    implementation(project(":feature:home"))
    implementation(project(":feature:calendar"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:login"))

    implementation(libs.hilt.navigation.compose)
}
