pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

rootProject.name = "NoWeekend"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":catalog")
include(":core:common-android")
include(":core:common-kotlin")
include(":core:common-ui")
include(":core:data")
include(":core:design-system")
include(":core:domain")
include(":core:local")
include(":core:model")
include(":core:navigator")
include(":core:remote")
include(":core:resource")
include(":feature:home")
include(":feature:sample")
