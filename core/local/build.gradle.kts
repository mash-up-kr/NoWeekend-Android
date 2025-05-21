plugins {
    alias(libs.plugins.team.noweekend.android.library)
    alias(libs.plugins.team.noweekend.hilt)
}

android {
    namespace = "team.noweekend.core.local"
}

dependencies {
    implementation(project(":core:common-kotlin"))
    implementation(project(":core:model"))
    implementation(libs.androidx.datastore.preferences)
}
