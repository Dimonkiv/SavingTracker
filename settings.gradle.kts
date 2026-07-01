pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Savings Tracker"
include(":app")
include(":core:navigation")
include(":core:mvi")
include(":core:common")
include(":core:designsystem")
include(":core:database")
include(":core:data:account-api")
include(":feature:select-icon")
include(":feature:account")
include(":feature:transaction")
include(":feature:main")
