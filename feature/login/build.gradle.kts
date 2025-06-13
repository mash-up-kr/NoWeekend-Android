import java.util.Properties

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

plugins {
    alias(libs.plugins.team.noweekend.android.feature)
}
android {
    namespace = "team.noweekend.feature.login"
    defaultConfig {
        buildConfigField("String", "GOOGLE_CLIENT_ID", "\"${localProperties["GOOGLE_CLIENT_ID"]}\"")
    }
}

dependencies {
    implementation(libs.bundles.google.login)
    implementation(libs.play.services.auth)
}
