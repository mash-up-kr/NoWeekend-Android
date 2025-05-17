package team.noweekend.convention

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project

internal fun ApplicationExtension.configureBuildConfig(project: Project) {
    defaultConfig {
        applicationId = "team.noweekend.app"
        targetSdk = project.findVersion("targetSdk").toInt()
        versionCode = project.findVersion("versionCode").toInt()
        versionName = project.findVersion("versionName")
    }
}