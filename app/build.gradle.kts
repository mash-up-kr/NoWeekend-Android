plugins {
    alias(libs.plugins.team.noweekend.android.application)
    alias(libs.plugins.team.noweekend.android.application.compose)
    alias(libs.plugins.team.noweekend.hilt)
}

android {
    namespace = "team.noweekend.app"
    
    defaultConfig {
        applicationId = "team.noweekend.app"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    // Core modules
    implementation(project(":core:common-android"))
    implementation(project(":core:common-kotlin"))
    implementation(project(":core:common-ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:local"))
    implementation(project(":core:model"))
    implementation(project(":core:navigator"))
    implementation(project(":core:remote"))
    implementation(project(":core:resource"))
    
    // Feature modules
    implementation(project(":feature:home"))
    
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)
}