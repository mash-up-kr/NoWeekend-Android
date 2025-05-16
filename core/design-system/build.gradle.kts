plugins {
    alias(libs.plugins.team.noweekend.android.library)
    alias(libs.plugins.team.noweekend.android.compose)
}

android {
    namespace = "team.noweekend.core.design.system"
}

dependencies {
    implementation(project(":core:common-kotlin"))
    implementation(project(":core:common-android"))
    implementation(project(":core:model"))
    implementation(project(":core:resource"))

    implementation(libs.androidx.material3)
}