package team.noweekend.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import java.io.FileInputStream
import java.util.Properties

internal fun ApplicationExtension.configureBuildFlavors(project: Project) {
    productFlavors {
        val properties = Properties()
        properties.load(FileInputStream("local.properties"))
        create("dev") {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            signingConfig = signingConfigs.getByName("dev")
            buildConfigField("int", "VERSION_CODE", "${project.findVersion("versionCode").toInt()}")
            buildConfigField("String", "VERSION_NAME", "\"${project.findVersion("versionName")}\"")
            resValue("string", "app_name", "NoWeekendDEV")
        }
        create("qa") {
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
            signingConfig = signingConfigs.getByName("qa")
            buildConfigField("int", "VERSION_CODE", "${project.findVersion("versionCode").toInt()}")
            buildConfigField("String", "VERSION_NAME", "\"${project.findVersion("versionName")}\"")
            resValue("string", "app_name", "NoWeekendQA")
        }
        create("prod") {
            signingConfig = signingConfigs.getByName("prod")
            buildConfigField("int", "VERSION_CODE", "${project.findVersion("versionCode").toInt()}")
            buildConfigField("String", "VERSION_NAME", "\"${project.findVersion("versionName")}\"")
            resValue("string", "app_name", "NoWeekend")
        }
    }
}

internal fun LibraryExtension.configureBuildFlavors(project: Project) {
    productFlavors {
        val properties = Properties()
        properties.load(FileInputStream("local.properties"))
        create("dev") {
            dimension = "version"
            buildConfigField("int", "VERSION_CODE", "${project.findVersion("versionCode").toInt()}")
            buildConfigField("String", "VERSION_NAME", "\"${project.findVersion("versionName")}\"")
        }
        create("qa") {
            dimension = "version"
            buildConfigField("int", "VERSION_CODE", "${project.findVersion("versionCode").toInt()}")
            buildConfigField("String", "VERSION_NAME", "\"${project.findVersion("versionName")}\"")
        }
        create("prod") {
            dimension = "version"
            buildConfigField("int", "VERSION_CODE", "${project.findVersion("versionCode").toInt()}")
            buildConfigField("String", "VERSION_NAME", "\"${project.findVersion("versionName")}\"")
        }
    }
}
