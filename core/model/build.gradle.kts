plugins {
    id("kotlin")
    alias(libs.plugins.team.noweekend.jvm.library)
    alias(libs.plugins.kotlinx.serialization)
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
}
