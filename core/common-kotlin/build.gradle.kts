plugins {
    id("kotlin")
    alias(libs.plugins.team.noweekend.jvm.library)
}

dependencies {
    implementation(project(":core:model"))
}
