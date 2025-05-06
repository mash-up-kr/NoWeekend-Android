plugins {
    id("kotlin")
    alias(libs.plugins.team.noweekend.jvm.library)
}
dependencies {
    implementation(project(":core:common-kotlin"))
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutine.core)
    implementation(libs.javax.inject)
}
