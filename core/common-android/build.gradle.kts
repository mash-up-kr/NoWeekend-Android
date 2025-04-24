plugins {
    alias(libs.plugins.team.noweekend.android.library)
    alias(libs.plugins.team.noweekend.hilt)
}

android {
    namespace = "team.noweekend.core.common.android"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common-kotlin"))
}
