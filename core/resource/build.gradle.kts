plugins {
    alias(libs.plugins.team.noweekend.android.library)
}

android {
    namespace = "team.noweekend.core.resource"
}

dependencies {
    implementation(project(":core:common-android"))
    implementation(project(":core:common-kotlin"))
    implementation(project(":core:common-ui"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material3)
}