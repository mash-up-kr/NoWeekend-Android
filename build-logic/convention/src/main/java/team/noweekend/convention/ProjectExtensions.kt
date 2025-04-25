package team.noweekend.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.findVersion(versionName: String): String {
    return checkNotNull(libs.findVersion(versionName).get().toString()) {
        "Version '$versionName' does not exist in the libs.versions.toml file."
    }
}
