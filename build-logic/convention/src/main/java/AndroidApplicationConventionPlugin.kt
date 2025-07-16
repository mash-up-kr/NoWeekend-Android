import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import team.noweekend.convention.configureAndroidSigning
import team.noweekend.convention.configureBuildConfig
import team.noweekend.convention.configureBuildFlavors
import team.noweekend.convention.configureBuildType
import team.noweekend.convention.configureKotlinAndroid

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureBuildConfig(this@with)
                configureAndroidSigning(this)
                configureBuildType()
                configureBuildFlavors(this@with)
            }
        }
    }
}
