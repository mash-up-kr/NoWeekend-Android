plugins {
    alias(libs.plugins.team.noweekend.android.library)
    alias(libs.plugins.team.noweekend.android.compose)
}

android {
    namespace = "team.noweekend.core.common.ui"
}

dependencies {
    implementation(project(":core:common-android"))
    implementation(project(":core:common-kotlin"))
    implementation(project(":core:model"))
    
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material3)
}