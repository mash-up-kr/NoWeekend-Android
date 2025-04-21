plugins {
    id("kotlin")
    alias(libs.plugins.team.noweekend.jvm.library)
}

dependencies {
    implementation(project(":core:common-android"))
    implementation(project(":core:common-kotlin"))
    implementation(project(":core:domain"))
}
