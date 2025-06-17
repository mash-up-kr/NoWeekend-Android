plugins {
    alias(libs.plugins.team.noweekend.android.application.compose)
    alias(libs.plugins.team.noweekend.hilt)
    alias(libs.plugins.kotlinx.serialization)
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
    implementation(project(":feature:sample"))
    implementation(project(":feature:calendar"))
    implementation(project(":feature:profile"))


    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.bundles.kotlinx.coroutine)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.collections.immutable)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
}
