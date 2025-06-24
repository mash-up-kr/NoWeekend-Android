plugins {
    alias(libs.plugins.team.noweekend.android.feature)
}

android {
    namespace = "team.noweekend.feature.login"
}

dependencies {
    implementation(libs.bundles.google.login)
    implementation(libs.play.services.auth)
}
