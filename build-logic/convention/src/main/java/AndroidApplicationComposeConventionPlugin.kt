import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import team.noweekend.convention.configureAndroidCompose
import team.noweekend.convention.configureBuildConfig
import team.noweekend.convention.configureBuildType
import team.noweekend.convention.configureKotlinAndroid

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                configureBuildConfig(this@with)
                configureBuildType()
            }
        }
    }
}
